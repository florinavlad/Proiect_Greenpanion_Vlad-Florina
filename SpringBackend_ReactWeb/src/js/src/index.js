import React from "react";
import ReactDOM from "react-dom/client";
import "../node_modules/antd/dist/antd";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import Admin from "./pages/admin-user/Admin";
import CreateUserForm from "./pages/forms/CreateUserForm";
import LoginUserForm from "./pages/forms/LoginUserForm";

const router = createBrowserRouter([
  {
    path: "/app",
    element: <App />,
  },
  {
    path: "/admin",
    element: <Admin />,
  },
  {
    path: "/register",
    element: <CreateUserForm />,
  },
  {
    path: "/login",
    element: <LoginUserForm />,
  },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<RouterProvider router={router} />);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
