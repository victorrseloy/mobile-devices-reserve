import { compose, applyMiddleware, combineReducers, createStore } from 'redux';
import { createEpicMiddleware } from 'redux-observable';
import user from 'reducers/userReducer';
import devices from 'reducers/devicesReducer';
import rootEpic from 'epics/rootEpic';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const epicMiddleware = createEpicMiddleware();
const store = createStore(
  combineReducers({ user, devices }),
  composeEnhancers(applyMiddleware(epicMiddleware))
);

epicMiddleware.run(rootEpic);
//assembles the app store
export default store;
