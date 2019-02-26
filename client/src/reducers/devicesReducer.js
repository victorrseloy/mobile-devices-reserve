import actionTypes from 'actions/actionTypes';
import { toast } from 'react-toastify';

const defaultState = [];

//reducer for device state
export default function devicesReducer(state = defaultState, action) {
  switch (action.type) {
    case actionTypes.DEVICES.DEVICES_LOADED:
      return action.payload;
    case actionTypes.DEVICES.BOOK_DEVICE_SUCCESS:
      toast.success(`Device sucessfully booked`, {
        position: toast.POSITION.TOP_RIGHT,
      });
      return [];
      break;
    case actionTypes.DEVICES.BOOK_DEVICE_FAILURE:
      toast.error(`It was not possible to book this device`, {
        position: toast.POSITION.TOP_RIGHT,
      });
      break;
    case actionTypes.DEVICES.RETURN_DEVICE_SUCCESS:
      toast.success(`Device sucessfully returned`, {
        position: toast.POSITION.TOP_RIGHT,
      });
      return [];
    case actionTypes.DEVICES.RETURN_DEVICE_FAILURE:
      toast.error(`It was not possible to return this device`, {
        position: toast.POSITION.TOP_RIGHT,
      });
      break;
    default:
      return state;
  }
  return state;
}
