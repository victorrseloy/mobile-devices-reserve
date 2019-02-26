import actionTypes from 'actions/actionTypes';

const defaultState = {
  token: '',
  auth: false,
};

export default function photosReducer(state = defaultState, action) {
  switch (action.type) {
    case actionTypes.USER.LOGIN_SUCCESS:
      return { token: action.payload, auth: true };
    default:
      return state;
  }
}
