import React from 'react';
import { Provider } from 'react-redux';
import 'semantic-ui-css/semantic.min.css';
import { Menu, Container } from 'semantic-ui-react';
import {
  BrowserRouter as Router,
  Route,
  Link,
  withRouter,
} from 'react-router-dom';

import LoginView from 'components/Views/LoginView';
import DevicesView from 'components/Views/DevicesView';
import store from 'store';

const PlainMenu = ({ match, location }) => {
  console.log(location);
  return (
    <Menu>
      <Menu.Item active={location.pathname === '/'}>
        <Link to="/">Devices</Link>
      </Menu.Item>
    </Menu>
  );
};

const NavMenu = withRouter(PlainMenu);

export default function App() {
  return (
    <Provider store={store}>
      <Router>
        <React.Fragment>
          <NavMenu />
          <Container textAlign="center">
            <Route path="/login" component={LoginView} />
            <Route path="/" component={DevicesView} />
          </Container>
        </React.Fragment>
      </Router>
    </Provider>
  );
}
