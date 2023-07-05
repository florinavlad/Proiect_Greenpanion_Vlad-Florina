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
  fetch("http://localhost:8080/api/v1/auth/admin", {}).then(checkStatus);

export const getRankedUsers = () =>
  fetch("http://localhost:8080/api/v1/auth/ranking", {}).then(checkStatus);

export const getUserInfo = () => {
  const accessToken = localStorage.getItem("accessToken");

  return fetch("http://localhost:8080/api/v1/auth/userInfo", {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  }).then((response) => {
    if (!response.ok) {
      throw new Error("Failed to get user info");
    }
    return response.json();
  });
};

export const register = (user) =>
  fetch("http://localhost:8080/api/v1/auth/register", {
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Headers": "X-Requested-With, Authorization",
    },

    method: "POST",
    body: JSON.stringify(user),
  })
    .then(checkStatus)
    .then((response) => {
      if (response.status === 200) {
        return response.text();
      } else if (response.status === 409) {
        throw new Error("Email already exists");
      } else {
        throw new Error("Error registering user");
      }
    });

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
