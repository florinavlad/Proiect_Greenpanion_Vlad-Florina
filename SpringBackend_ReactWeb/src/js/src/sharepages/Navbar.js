import { useRef, useEffect, useState } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import { Link } from "react-scroll";
import Greenpanion_Icon from "../assets/Greenpanion_Icon.png";
import "./spages.css";

function Navbar(props) {
  const navbarRef = useRef();
  const displayNavbar = () => {
    navbarRef.current.classList.toggle("responsive-navbar");
  };
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, [Boolean(props.isLoggedIn)]);

  const handleLogOut = () => {
    props.clickLogOut();
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
        <a href="/app">Acasă</a>

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

        <Link
          to="how-to-recycle"
          spy={true}
          smooth={true}
          offset={-150}
          duration={500}
        >
          Cum reciclez?
        </Link>

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
