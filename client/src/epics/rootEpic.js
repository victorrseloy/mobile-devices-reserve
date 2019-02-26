import { Observable, interval } from 'rxjs';
import { combineEpics } from 'redux-observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/ignoreElements';
import 'rxjs/add/observable/dom/ajax';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toArray';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/debounce';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/from';
import 'rxjs/add/operator/delay';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/concat';
import 'rxjs/add/observable/forkJoin';
import actionTypes from 'actions/actionTypes';

import * as userService from 'services/userService';
import * as userAction from 'actions/userActions';

import * as phoneBookingService from 'services/phoneBookingService';
import * as devicesActions from 'actions/devicesActions';


/**
 * Epics file, this file controlss the side effect calls
 * @param $action
 */

//login side effect observable
function loginEpic($action) {
  return $action
    .ofType(actionTypes.USER.START_LOGIN)
    .switchMap(({ payload }) => {
      return Observable.from(
        userService.getAuthToken(payload.username, payload.password)
      )
        .map(token => userAction.loginSuccess(token))
        .catch(() => Observable.of(userAction.loginFailure()));
    });
}

//register side effect observable
function registerEpic($action) {
  return $action.ofType(actionTypes.USER.REGISTER).switchMap(({ payload }) => {
    return Observable.from(
      userService.register(payload.username, payload.password)
    )
      .map(() => userAction.startLogin(payload.username, payload.password))
      .catch(err => Observable.of(userAction.registerFailure(err)));
  });
}

function loadDevicesEpic($action) {
  return $action
    .ofType(actionTypes.DEVICES.LOAD_DEVICES)
    .switchMap(({ payload }) => {
      return Observable.from(phoneBookingService.getDevices(payload))
        .map(devices => devicesActions.loadDevicesSuccess(devices))
        .catch(err => Observable.of(devicesActions.loadDevicesFailuer(err)));
    });
}

//this epic debounces the calls to make sure that we don't call the server multiple times
function bookDeviceEpic($action) {
  return $action
    .ofType(actionTypes.DEVICES.BOOK_DEVICE)
    .debounce(() => interval(500))
    .switchMap(({ payload }) => {
      return Observable.from(
        phoneBookingService.bookPhone(payload.deviceId, payload.token)
      )
        .map(devices => devicesActions.bookDeviceSuccess(payload.token))
        .catch(err => Observable.of(devicesActions.bookDeviceFailure(err)));
    });
}

//this epic debounces the calls to make sure that we don't call the server multiple times
function returnDeviceEpic($action) {
  return $action
    .ofType(actionTypes.DEVICES.RETURN_DEVICE)
    .debounce(() => interval(500))
    .switchMap(({ payload }) => {
      return Observable.from(
        phoneBookingService.returnPhone(payload.deviceId, payload.token)
      )
        .map(devices => devicesActions.returnDeviceSuccess(payload.token))
        .catch(err => Observable.of(devicesActions.returnDeviceFailure(err)));
    });
}

//in case of a success book or return this epic is called to refesh the app
function refreshViewEpic($action) {
  return $action
    .ofType(
      actionTypes.DEVICES.BOOK_DEVICE_SUCCESS,
      actionTypes.DEVICES.RETURN_DEVICE_SUCCESS
    )
    .switchMap(({ payload }) => {
      return Observable.from(phoneBookingService.getDevices(payload))
        .map(devices => devicesActions.loadDevicesSuccess(devices))
        .catch(err => Observable.of(devicesActions.loadDevicesFailuer(err)));
    });
}

const rootEpic = combineEpics(
  loginEpic,
  registerEpic,
  loadDevicesEpic,
  bookDeviceEpic,
  returnDeviceEpic,
  refreshViewEpic
);
// const rootEpic = combineEpics(fetchphotos);

export default rootEpic;
