import React from "react";
import { Formik } from "formik";
import { Input, Button, Tag } from "antd";
import { LoginUserFormSchema } from "./schemas";
import { login } from "../../client";

const LoginUserForm = (props) => (
  <Formik
    initialValues={{
      email: "",
      password: "",
    }}
    validationSchema={LoginUserFormSchema}
    onSubmit={(user, { resetForm }) => {
      login(user).then((response) => {
        console.log(response);
        if (response.accessToken) {
          localStorage.setItem("accessToken", response.accessToken);
          props.onSuccess();
        }
      });
      resetForm();
    }}
  >
    {({
      values,
      errors,
      touched,
      handleChange,
      handleBlur,
      handleSubmit,
      isSubmitting,
      submitForm,
      isValid,
      resetForm,
    }) => (
      <form onSubmit={handleSubmit}>
        <div className="input-block">
          <label htmlFor="email" className="input-label">
            EMAIL
          </label>
          <Input
            type="email"
            name="email"
            autoComplete="off"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.email}
            placeholder="Email. exemple@gmail.com"
          />
          {errors.email && touched.email && <Tag>{errors.email}</Tag>}
        </div>

        <div className="input-block" id="login-password">
          <label htmlFor="password" className="input-label">
            PAROLĂ
          </label>
          <Input
            type="password"
            name="password"
            autoComplete="off"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.password}
            placeholder="Parolă"
          />
          {errors.password && touched.password && <Tag>{errors.password}</Tag>}
        </div>

        <div className="modal-buttons">
          <Button
            onClick={() => submitForm()}
            disabled={isSubmitting | (touched && !isValid)}
            type="submit"
          >
            Autentificare
          </Button>
        </div>
      </form>
    )}
  </Formik>
);

export default LoginUserForm;
