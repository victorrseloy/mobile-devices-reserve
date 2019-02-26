import actionTypes from 'actions/actionTypes';
import { toast } from 'react-toastify';

const defaultState = {
  token: '',
  auth: false,
  email: '',
};

export default function photosReducer(state = defaultState, action) {
  switch (action.type) {
    case actionTypes.USER.START_LOGIN:
      return { ...state, email: action.payload.username };
    case actionTypes.USER.LOGIN_SUCCESS:
      return { ...state, token: action.payload, auth: true };
    case actionTypes.USER.LOGIN_FAILURE:
      toast.error('It was not possible to authenticate you', {
        position: toast.POSITION.TOP_RIGHT,
      });
      break;
    case actionTypes.USER.REGISTER_FAILURE:
      toast.error(
        `It was not possible to register you with the following reason`,
        {
          position: toast.POSITION.TOP_RIGHT,
        }
      );
      break;
    default:
      return state;
  }
  return state;
}
