import actionTypes from './actionTypes';

export function loadDevices(token) {
  return {
    type: actionTypes.DEVICES.LOAD_DEVICES,
    payload: token,
  };
}

export function loadDevicesSuccess(devices) {
  return {
    type: actionTypes.DEVICES.DEVICES_LOADED,
    payload: devices,
  };
}

export function loadDevicesFailuer(err) {
  return {
    type: actionTypes.DEVICES.DEVICES_LOADED_FAILURE,
    payload: err,
  };
}

export function bookDevice(deviceId, token) {
  return {
    type: actionTypes.DEVICES.BOOK_DEVICE,
    payload: { token, deviceId },
  };
}

export function bookDeviceSuccess() {
  return {
    type: actionTypes.DEVICES.BOOK_DEVICE_SUCCESS,
  };
}

export function bookDeviceFailure(err) {
  return {
    type: actionTypes.DEVICES.BOOK_DEVICE_FAILURE,
    payload: err,
  };
}

export function returnDevice(deviceId, token) {
  return {
    type: actionTypes.DEVICES.RETURN_DEVICE,
    payload: { token, deviceId },
  };
}

export function returnDeviceSuccess() {
  return {
    type: actionTypes.DEVICES.RETURN_DEVICE_SUCCESS,
  };
}

export function returnDeviceFailure(err) {
  return {
    type: actionTypes.DEVICES.RETURN_DEVICE_FAILURE,
    payload: err,
  };
}
