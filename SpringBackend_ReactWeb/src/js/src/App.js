import "./App.css";
import "./pages/map-prizes/map-prizes.css";
import Navbar from "./sharepages/Navbar";
import Footer from "./sharepages/Footer";
import CreateUserForm from "./pages/forms/CreateUserForm";
import React, { useState } from "react";
import { Modal } from "antd";
import { errorNote } from "./Notification";
import LoginUserForm from "./pages/forms/LoginUserForm";
import About from "./pages/about-us/About";
import Map from "./pages/map-prizes/Map";
import Prizes from "./pages/map-prizes/Prizes";

const App = () => {
  const [isAdduserOpen, setIsAdduserOpen] = useState();
  const [isLoginOpen, setIsLoginOpen] = useState();

  const showModal = () => {
    setIsAdduserOpen(true);
  };
  const handleOk = () => {
    setIsAdduserOpen(false);
  };
  const handleCancel = () => {
    setIsAdduserOpen(false);
  };

  const showLogin = () => {
    setIsLoginOpen(true);
  };

  const handleLogOk = () => {
    setIsLoginOpen(false);
  };
  const handleLogCancel = () => {
    setIsLoginOpen(false);
  };

  return (
    <div>
      <div className="background">
        <div className="content">
          <h1>
            Let's <br />
            <span>SAVE</span> our <span>PLANET</span> <br />
            together
          </h1>
          <a href="#">DOWNLOAD APP</a>
        </div>
      </div>
      <Navbar clickSignUp={showModal} clickLogIn={showLogin}></Navbar>
      <Modal
        title="Crează cont nou"
        open={isAdduserOpen}
        onOk={handleOk}
        onCancel={handleCancel}
        width={500}
      >
        <CreateUserForm
          onSuccess={() => {
            handleCancel();
          }}
          onFailure={(error) => {
            const message = error.error.message;
            const description = error.error.httpStatus;
            errorNote(message, description);
          }}
        ></CreateUserForm>
      </Modal>
      <Modal
        title="Bine ai revenit!"
        open={isLoginOpen}
        onOk={handleLogOk}
        onCancel={handleLogCancel}
        width={500}
      >
        <LoginUserForm
          onSuccess={() => {
            handleLogCancel();
          }}
        ></LoginUserForm>
      </Modal>
      <About></About>
      <Map></Map>
      <Prizes></Prizes>
      <Footer></Footer>
    </div>
  );
};

export default App;
