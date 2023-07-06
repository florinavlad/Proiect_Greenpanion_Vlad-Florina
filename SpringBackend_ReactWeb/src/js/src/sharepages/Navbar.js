import { useRef, useEffect, useState } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import { Link } from "react-scroll";
import Greenpanion_Icon from "../assets/Greenpanion_Icon.png";
import "./spages.css";
import { useNavigate } from "react-router-dom";

function Navbar(props) {
  const navbarRef = useRef();
  const displayNavbar = () => {
    navbarRef.current.classList.toggle("responsive-navbar");
  };
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();
  // const handleSuccessRedirect = () => {
  //   navigate("/");
  // };
  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      setIsLoggedIn(true);
    }
  }, []);
  const clickLogOut = () => {
    localStorage.removeItem("accessToken");
    setIsLoggedIn(false);
  };
  const handleLogOut = () => {
    clickLogOut();
    navigate("/");
  };

  return (
    <header>
      <div className="brandName">
        <img
          className="brandIcon"
          src={Greenpanion_Icon}
          alt="Greepanion Icon"
        ></img>
        Greenpanion
      </div>
      <nav ref={navbarRef}>
        <a href="/">Acasă</a>

        <Link to="about" spy={true} smooth={true} offset={-100} duration={500}>
          Despre noi
        </Link>

        <button
          className="btn-createuserform"
          type="primary"
          onClick={props.clickSignUp}
        >
          Înregistrare
        </button>

        <button
          className="btn-loginuserform"
          type="primary"
          onClick={props.clickLogIn}
        >
          Autentificare
        </button>

        {isLoggedIn && (
          <>
            <a href="/admin">Statistici</a>
            <button className="btn-logout" onClick={handleLogOut}>
              Delogare
            </button>
          </>
        )}

        <button className="close-navbtn" onClick={displayNavbar}>
          <FaTimes />
        </button>
      </nav>
      <button className="hamburger-navbtn" onClick={displayNavbar}>
        <FaBars />
      </button>
    </header>
  );
}

export default Navbar;
