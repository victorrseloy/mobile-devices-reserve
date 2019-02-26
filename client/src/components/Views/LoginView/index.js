import React from 'react';
import { Button, Form, Grid, Header, Segment } from 'semantic-ui-react';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { startLogin, register } from 'actions/userActions';
import { withState, withHandlers, compose, lifecycle } from 'recompose';
import { withRouter } from 'react-router';

/**
 *
 * Main login view as a pure component, this component uses recompose as an alternative to the class components
 *
 *
 */
const LoginView = ({
  onEmailChange,
  onPasswordChange,
  email,
  password,
  loginHandler,
  registerHandler,
}) => (
  <Grid centered columns={2}>
    <Grid.Column>
      <Header as="h2" textAlign="center">
        Login
      </Header>
      <Segment>
        <Form size="large">
          <Form.Input
            fluid
            icon="user"
            iconPosition="left"
            placeholder="Email address"
            value={email}
            onChange={onEmailChange}
          />
          <Form.Input
            fluid
            icon="lock"
            iconPosition="left"
            placeholder="Password"
            type="password"
            value={password}
            onChange={onPasswordChange}
          />
          <Button color="blue" fluid size="large" onClick={loginHandler}>
            Login
          </Button>
          <Button color="gray" fluid size="large" onClick={registerHandler}>
            register
          </Button>
        </Form>
      </Segment>
    </Grid.Column>
  </Grid>
);

const mapStateToProps = state => ({
  auth: state.user.auth,
  token: state.user.token,
});

const mapDispatchToProps = dispatch =>
  bindActionCreators({ startLogin, register }, dispatch);

/**
 * Component enhancers
 */
const EnhancedLoginView = compose(
  connect(
    mapStateToProps,
    mapDispatchToProps
  ),
  withRouter,
  withState('email', 'setEmail', ''),
  withState('password', 'setPassword', ''),
  withHandlers({
    onEmailChange: ({ setEmail }) => e => setEmail(e.target.value),
    onPasswordChange: ({ setPassword }) => e => setPassword(e.target.value),
    loginHandler: ({ startLogin, email, password }) => () =>
      startLogin(email, password),
    registerHandler: ({ register, email, password }) => () =>
      register(email, password),
  }),
  lifecycle({
    componentDidUpdate() {
      if (this.props.auth) {
        this.props.history.push('/');
      }
    },
  })
)(LoginView);

export default EnhancedLoginView;
