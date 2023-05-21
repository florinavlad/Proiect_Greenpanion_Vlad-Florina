import { notification } from "antd";

const openNote = (type, message, description) => {
  notification[type]({
    message,
    description,
  });
};

export const successNote = (message, description) =>
  openNote("sucess", message, description);

export const infosNote = (message, description) =>
  openNote("info", message, description);

export const warningNote = (message, description) =>
  openNote("warning", message, description);

export const errorNote = (message, description) =>
  openNote("error", message, description);
