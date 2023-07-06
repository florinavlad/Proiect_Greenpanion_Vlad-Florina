import React from "react";
import { Formik } from "formik";
import { Input, Button, Tag } from "antd";
import { LoginUserFormSchema } from "./schemas";
import { login } from "../../client";
import { useState } from "react";

const LoginUserForm = (props) => {
  const [error, setError] = useState("");
  const handleFormSubmit = (values, { resetForm }) => {
    login(values)
      .then((response) => {
        console.log(response);
        if (response.accessToken) {
          localStorage.setItem("accessToken", response.accessToken);
          props.onSuccess();
        } else {
          setError("");
        }
      })
      .catch((error) => {
        console.log(error);
        setError("Datele introduse sunt invalide :(");
        setTimeout(() => {
          setError("");
        }, 3000);
      });
    resetForm();
  };
  return (
    <Formik
      initialValues={{
        email: "",
        password: "",
      }}
      validationSchema={LoginUserFormSchema}
      onSubmit={handleFormSubmit}
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
          </div>

          {error && <Tag className="error-message">{error}</Tag>}

          <div className="modal-buttons">
            <Button
              onClick={() => submitForm()}
              disabled={isSubmitting | !isValid}
              type="submit"
            >
              Autentificare
            </Button>
          </div>
        </form>
      )}
    </Formik>
  );
};

export default LoginUserForm;
