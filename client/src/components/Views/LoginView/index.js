import React from 'react';
import { Button, Form, Grid, Header, Segment } from 'semantic-ui-react';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { startLogin, register } from 'actions/userActions';
import { withState, withHandlers, compose } from 'recompose';

const LoginView = ({
  onEmailChange,
  onPasswordChange,
  email,
  password,
  startLogin,
  register,
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
          <Button
            color="blue"
            fluid
            size="large"
            onClick={() => startLogin(email, password)}
          >
            Login
          </Button>
          <Button
            color="gray"
            fluid
            size="large"
            onClick={() => register(email, password)}
          >
            register
          </Button>
        </Form>
      </Segment>
    </Grid.Column>
  </Grid>
);

const EnhancedLoginView = compose(
  withState('email', 'setEmail', ''),
  withState('password', 'setPassword', ''),
  withHandlers({
    onEmailChange: ({ setEmail }) => e => setEmail(e.target.value),
    onPasswordChange: ({ setPassword }) => e => setPassword(e.target.value),
    login: ({ startLogin, email, password }) => () =>
      startLogin(email, password),
  })
)(LoginView);

const mapStateToProps = state => ({
  auth: state.user.auth,
  token: state.user.token,
});

const mapDispatchToProps = dispatch =>
  bindActionCreators({ startLogin, register }, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnhancedLoginView);
