import fetch from "../node_modules/unfetch/dist/unfetch";

const checkStatus = (response) => {
  if (response.ok) {
    return response;
  } else {
    let error = new Error(response.statusText);
    error.response = response;
    response.json().then((e) => {
      error.error = e;
    });
    return Promise.reject(error);
  }
};

export const getAllUsers = (user) =>
  fetch("http://localhost:8080/api/v1/auth/admin", {
    // headers: {
    //   "Content-Type": "application/json",
    //   "Access-Control-Allow-Origin": "*",
    //   "Access-Control-Allow-Headers": "X-Requested-With, Authorization",
    // },
    // method: "GET",
    // body: JSON.stringify(user),
  }).then(checkStatus);

export const register = (user) =>
  fetch("http://localhost:8080/api/v1/auth/register", {
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Headers": "X-Requested-With, Authorization",
    },

    method: "POST",
    body: JSON.stringify(user),
  }).then(checkStatus);

export const login = (user) =>
  fetch("http://localhost:8080/api/v1/auth/authenticate", {
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Headers": "X-Requested-With, Authorization",
    },

    method: "POST",
    body: JSON.stringify(user),
  }).then((response) => response.json());
