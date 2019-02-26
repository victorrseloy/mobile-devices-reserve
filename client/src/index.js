import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import "semantic-ui-css/semantic.min.css";
import App from 'main/App';
import * as serviceWorker from './serviceWorker';
import * as userService from 'services/userService';
import * as phoneBookingService from 'services/phoneBookingService';

ReactDOM.render(<App />, document.getElementById('root'));
userService.getAuthToken('victorrseloy2@hotmail.com','123456')
userService.register('victorrseloy3@hotmail.com','123456')
phoneBookingService.getDevices('77967c44-2dd5-4ec7-b99a-4a45028dc1c0');
phoneBookingService.bookPhone(45,'77967c44-2dd5-4ec7-b99a-4a45028dc1c0');
phoneBookingService.returnPhone(45,'77967c44-2dd5-4ec7-b99a-4a45028dc1c0');

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
