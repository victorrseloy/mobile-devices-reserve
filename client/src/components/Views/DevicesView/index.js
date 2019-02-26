import React, { Component } from 'react';
import {Button, Table, Dimmer, Loader, Label} from 'semantic-ui-react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { loadDevices, bookDevice, returnDevice } from 'actions/devicesActions';
import Capabilities from 'components/Presentational/Capabilites';

/**
 * Devices view, controlss the devices lists
 */
class DevicesView extends Component {

  componentDidMount() {
    if (!this.props.auth) {
      this.props.history.push('/login');
    }
    else{
      this.props.loadDevices(this.props.token);
    }

  }

  componentDidUpdate() {
    if (!this.props.auth) {
      this.props.history.push('/login');
    }
  }

  render() {
    return (
      <div>
        <h1>Devices</h1>
        <Dimmer active={!this.props.devices.length}>
          <Loader>Loading</Loader>
        </Dimmer>
        <Table celled>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Status</Table.HeaderCell>
              <Table.HeaderCell>Device</Table.HeaderCell>
              <Table.HeaderCell>Capabilities</Table.HeaderCell>
              <Table.HeaderCell>Current Booker</Table.HeaderCell>
              <Table.HeaderCell>Booked date</Table.HeaderCell>
              <Table.HeaderCell>Actions</Table.HeaderCell>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {this.props.devices && this.props.devices.map( (item) => (
              <Table.Row key={item.id}>
                <Table.Cell>
                  {item.available && <Label ribbon>Available</Label>}
                  {!item.available && <Label ribbon>Not Available</Label>}
                </Table.Cell>
                <Table.Cell>{item.brand} {item.name}</Table.Cell>
                <Table.Cell>
                  <Capabilities capabilities={item.phoneCapabilities} />
                </Table.Cell>
                <Table.Cell>{item.currentUser}</Table.Cell>
                <Table.Cell>{item.bookedDate}</Table.Cell>
                <Table.Cell>
                  {!!item.available && <Button onClick={() => this.props.bookDevice(item.id,this.props.token)} content='Book item' primary />}
                  {!item.available && !!(item.currentUser === this.props.userEmail) && <Button onClick={() => this.props.returnDevice(item.id,this.props.token)} content='Return item' primary />}
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  auth: state.user.auth,
  token: state.user.token,
  devices: state.devices,
  userEmail: state.user.email,
});

const mapDispatchToProps = dispatch =>
  bindActionCreators({ loadDevices,bookDevice, returnDevice }, dispatch);

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DevicesView);
