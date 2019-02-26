import React from 'react';
import { List } from 'semantic-ui-react';

/**
 * Component that displays the phone capabilities
 *
 * @param capabilities
 * @returns {*}
 * @constructor
 */
const Capabilities = ({ capabilities }) => {
  if (!capabilities) {
    return <span> no Info available</span>;
  }
  return (
    <List as="ul">
      {!!capabilities.technology && (
        <List.Item as="li">
          Technology{' '}
          {capabilities.technology}
        </List.Item>
      )}
      {!!capabilities.bands2g && (
        <List.Item as="li">
          Bands 2g{' '}
          {capabilities.bands2g}{' '}
        </List.Item>
      )}
      {!!capabilities.bands3g && (
        <List.Item as="li">
          Bands 2g{' '}
          {capabilities.bands3g}
        </List.Item>
      )}
      {!!capabilities.bands4g && (
        <List.Item as="li">
          Bands 4g{' '}
          {capabilities.bands2g}
        </List.Item>
      )}
    </List>
  );
};

export default Capabilities;
