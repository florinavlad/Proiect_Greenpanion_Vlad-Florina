import { useRef } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import { Link } from "react-scroll";
import Greenpanion_Icon from "../assets/Greenpanion_Icon.png";
import "./spages.css";

function Navbar(props) {
  const navbarRef = useRef();
  const displayNavbar = () => {
    navbarRef.current.classList.toggle("responsive-navbar");
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
        <Link to="app" spy={true} smooth={true} offset={-100} duration={500}>
          Acasă
        </Link>
        <Link to="about" spy={true} smooth={true} offset={-100} duration={500}>
          Despre noi
        </Link>

        <button
          className="btn-createuserform"
          type="primary"
          onClick={props.clickSignUp}
        >
          <a href="/#">Înregistrare</a>
        </button>

        <button
          className="btn-loginuserform"
          type="primary"
          onClick={props.clickLogIn}
        >
          <a href="/#">Autentificare</a>
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
