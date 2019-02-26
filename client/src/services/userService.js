import axios from 'axios';
import qs from 'qs';

const AUTH_ENDPOINT = '/oauth/token';
const REGISTER_ENDPOINT = '/api/v1/user/';
const clientIdentifierHeader = {
  Authorization: 'Basic cHJvamVjdF9jbGllbnQ6cHJvamVjdF9wYXNz',
};

export async function getAuthToken(username, password) {
  const headers = {
    ...clientIdentifierHeader,
    'Content-Type': 'application/x-www-form-urlencoded',
    Accept: 'application/json',
  };

  const data = {
    grant_type: 'password',
    scope: 'read write',
    username,
    password,
  };

  const response = await axios.request({
    url: AUTH_ENDPOINT,
    method: 'post',
    data: qs.stringify(data),
    headers,
  });

  console.log(response);
  return response.data.access_token;
}

export async function register(email, password) {
  const headers = {
    Accept: 'application/json',
    'Content-Type': 'application/x-www-form-urlencoded',
  };

  const data = {
    email,
    password,
  };

  const response = await axios.request({
    url: REGISTER_ENDPOINT,
    method: 'post',
    data: qs.stringify(data),
    headers,
  });

  console.log(response);
  return response.data;
}
