import actionTypes from './actionTypes';

/**
 * action generators for the user actions
 *
 * @param devices
 * @returns {{payload: *, type: string}}
 */

export function startLogin(username, password) {
  return {
    type: actionTypes.USER.START_LOGIN,
    payload: { username, password },
  };
}

export function loginSuccess(token) {
  return {
    type: actionTypes.USER.LOGIN_SUCCESS,
    payload: token,
  };
}

export function loginFailure() {
  return {
    type: actionTypes.USER.LOGIN_FAILURE,
  };
}

export function register(username, password) {
  return {
    type: actionTypes.USER.REGISTER,
    payload: { username, password },
  };
}

export function registerFailure(err) {
  return {
    type: actionTypes.USER.REGISTER_FAILURE,
    payload: err,
  };
}
