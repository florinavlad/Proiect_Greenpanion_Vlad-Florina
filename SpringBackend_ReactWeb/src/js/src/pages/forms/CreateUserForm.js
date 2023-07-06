import React, { useState } from "react";
import { useFormik } from "formik";
import { Input, Button, Tag, Select } from "antd";
import { register } from "../../client";
import { UserFormSchema } from "./schemas";
import { City, Country, State } from "country-state-city";
import "../forms/formStyle.css";
import { Link } from "react-router-dom";

function CreateUserForm(props) {
  const [errorMessage, setErrorMessage] = useState("");

  const createUser = useFormik({
    initialValues: {
      firstName: undefined,
      lastName: undefined,
      state: undefined,
      city: undefined,
      email: undefined,
      password: undefined,
      confirm_password: undefined,
    },

    validationSchema: UserFormSchema,

    onSubmit: (values, { setSubmitting }) => {
      register(values)
        .then(() => {
          props.onSuccessRedirect();
        })
        .catch((error) => {
          if (error.response && error.response.status === 409) {
            setErrorMessage("Acest email există deja ca utilizator");
          } else {
            setErrorMessage("Eroare înregistrare");
          }
          alert(errorMessage);
        })
        .finally(() => {
          setSubmitting(false);
        });
    },
  });

  const {
    values,
    errors,
    touched,
    handleChange,
    handleBlur,
    handleSubmit,
    isSubmitting,
    submitForm,
    isValid,
    setValues,
    setFieldValue,
  } = createUser;

  return (
    <form onSubmit={handleSubmit}>
      <div className="input-block">
        <label htmlFor="firstName" className="input-label">
          PRENUME
        </label>
        <Input
          id="firstName"
          name="firstName"
          autoComplete="off"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.firstName}
          placeholder="Prenume"
        />
        {errors.firstName && touched.firstName && <Tag>{errors.firstName}</Tag>}
      </div>
      <div className="input-block">
        <label htmlFor="lastName" className="input-label">
          NUME
        </label>
        <Input
          id="lastName"
          name="lastName"
          autoComplete="off"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.lastName}
          placeholder="Nume"
        />
        {errors.lastName && touched.lastName && <Tag>{errors.lastName}</Tag>}
      </div>

      <div className="input-block">
        <label htmlFor="address" className="input-label">
          ADRESĂ
        </label>
        <label htmlFor="state" className="input-label">
          Județ
        </label>
        <select
          id="state"
          name="state"
          placeholder="state"
          onBlur={handleBlur}
          value={values.state}
          onChange={handleChange}
        >
          <option>--Alege județul--</option>
          {State?.getStatesOfCountry("RO")?.map((e, key) => {
            return (
              <option value={e.name} key={key}>
                {e.name}
              </option>
            );
          })}
        </select>
        {errors.state && touched.state && <Tag>{errors.state}</Tag>}
        <label htmlFor="city" className="input-label">
          Oraș
        </label>
        <select
          id="city"
          name="city"
          placeholder="City"
          value={values.city}
          onChange={handleChange}
          onBlur={handleBlur}
        >
          <option>--Alege orașul--</option>
          {City?.getCitiesOfState(
            "RO",
            State?.getStatesOfCountry("RO")?.find(
              (s) => s.name === values.state
            )?.isoCode
          )?.map((e, key) => {
            return (
              <option value={e.name} key={key}>
                {e.name}
              </option>
            );
          })}
        </select>
        {errors.city && touched.city && <Tag>{errors.city}</Tag>}
      </div>

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
      <div className="input-block" id="password">
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
      <div className="input-block" id="password">
        <label htmlFor="confirm_password" className="input-label">
          CONFIRMĂ PAROLA
        </label>
        <Input
          type="password"
          name="confirm_password"
          autoComplete="off"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.confirm_password}
          placeholder="Confirmă parola"
        />
        {errors.confirm_password && touched.confirm_password && (
          <Tag>{errors.confirm_password}</Tag>
        )}
      </div>
      <div className="sign-up">
        <Link to="/">
          Ai deja cont? <span>Autentifică-te</span>.
        </Link>

        <Button
          id="submit"
          name="submit"
          type="submit"
          onClick={() => submitForm()}
          disabled={isSubmitting | !isValid}
        >
          Înregistrare
        </Button>
      </div>

      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </form>
  );
}

export default CreateUserForm;
