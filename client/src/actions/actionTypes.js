/**
 * contain all possoble action types of the application
 *
 * @type {{REGISTER_FAILURE: string, REGISTER: string, START_LOGIN: string, LOGIN_SUCCESS: string, LOGIN_FAILURE: string}}
 */

const USER = {
  START_LOGIN: 'START_LOGIN@USER',
  LOGIN_SUCCESS: 'LOGIN_SUCCESS@USER',
  LOGIN_FAILURE: 'LOGIN_FAILURE@USER',
  REGISTER: 'REGISTER@USER',
  REGISTER_FAILURE: 'REGISTER_FAILURE@USER',
};

const DEVICES = {
  LOAD_DEVICES: 'LOAD_DEVICES@DEVICES',
  DEVICES_LOADED: 'DEVICES_LOADED@DEVICES',
  DEVICES_LOADED_FAILURE: 'DEVICES_LOADED_FAILURE@DEVICES',
  BOOK_DEVICE: 'BOOK_DEVICE@DEVICES',
  BOOK_DEVICE_SUCCESS: 'BOOK_DEVICE_SUCCESS@DEVICES',
  BOOK_DEVICE_FAILURE: 'DEVICES_BOOK_DEVICE@DEVICES',
  RETURN_DEVICE: 'RETURN_DEVICE@DEVICES',
  RETURN_DEVICE_SUCCESS: 'RETURN_DEVICE_SUCCESS@DEVICES',
  RETURN_DEVICE_FAILURE: 'RETURN_DEVICE_FAILURE@DEVICES',
};

export default { USER, DEVICES };
