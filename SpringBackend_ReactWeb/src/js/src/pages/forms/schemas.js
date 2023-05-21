import * as yup from "yup";

const emailRequirements =
  "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
  "\\@" +
  "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
  "(" +
  "\\." +
  "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
  ")+";
const passwordRequirements = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
// min 6 characters, 1 upper case letter, 1 numeric digit

export const UserFormSchema = yup.object().shape({
  firstName: yup.string().required("Obligatoriu"),

  lastName: yup.string().required("Obligatoriu"),

  email: yup
    .string()
    .matches(emailRequirements, {
      message: "Introduceți un email valid",
    })
    .required("Obligatoriu"),

  state: yup.string().required("Selectați județul"),
  city: yup.string().required("Selectați orașul"),

  password: yup
    .string()
    .min(6)
    .matches(passwordRequirements, {
      message: "Crează o parolă mai puternică",
    })
    .required("Obligatoriu"),

  confirm_password: yup
    .string()
    .oneOf([yup.ref("password"), null], "Parolele trebuie să fie identice")
    .required("Obligatoriu"),
});

export const LoginUserFormSchema = yup.object().shape({
  email: yup
    .string()
    .matches(emailRequirements, {
      message: "Introduceți un email valid",
    })
    .required("Required"),

  password: yup.string().required("Required"),
});
