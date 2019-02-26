import axios from 'axios';
import qs from 'qs';

const PHONE_LIST_ENDPOINT = '/api/v1/phone/';
const BOOK_PHONE_ENDPOINT = '/api/v1/phone/book/';
const RETURN_PHONE_ENDPOINT = '/api/v1/phone/return/';

export async function getDevices(authToken) {
  const headers = {
    Authorization: `Bearer ${authToken}`,
    Accept: 'application/json',
  };

  const data = {};

  const response = await axios.request({
    url: PHONE_LIST_ENDPOINT,
    method: 'get',
    data: qs.stringify(data),
    headers,
  });

  console.log(response);
  return response.data;
}

export async function bookPhone(phoneId, authToken) {
  const headers = {
    Authorization: `Bearer ${authToken}`,
    'Content-Type': 'application/x-www-form-urlencoded',
    Accept: 'application/json',
  };

  const data = {
    phoneId,
  };

  const response = await axios.request({
    url: BOOK_PHONE_ENDPOINT,
    method: 'post',
    data: qs.stringify(data),
    headers,
  });

  console.log(response);
  return response.data;
}

export async function returnPhone(phoneId, authToken) {
  const headers = {
    Authorization: `Bearer ${authToken}`,
    'Content-Type': 'application/x-www-form-urlencoded',
    Accept: 'application/json',
  };

  const data = {
    phoneId,
  };

  const response = await axios.request({
    url: RETURN_PHONE_ENDPOINT,
    method: 'post',
    data: qs.stringify(data),
    headers,
  });

  console.log(response);
  return response.data;
}
