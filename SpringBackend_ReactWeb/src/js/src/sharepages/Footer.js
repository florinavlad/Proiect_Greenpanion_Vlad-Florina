import React from "react";
import "./spages.css";
import {
  FacebookOutlined,
  InstagramOutlined,
  AndroidOutlined,
} from "@ant-design/icons";

const Footer = () => {
  return (
    <div className="footer">
      <div className="footer-container">
        <div className="footer-col">
          <h4>info</h4>
          <p>
            Focuses on primary recycle materials paper, plastic and metal. The
            main purpose of the Greenpanion application is to help people
            recycle in a pleasant way, with prizes to match.
          </p>
        </div>

        <div className="footer-col">
          <h4>servicii clienți</h4>
          <ul>
            <li>
              <a href="#">Ajutor și contact</a>
            </li>
            <li>
              <a href="#">Termeni și condiții</a>
            </li>
            <li>
              <a href="#">Relații cu investitorii</a>
            </li>
            <li>
              <a href="#">Politica de confidențialitate</a>
            </li>
          </ul>
        </div>

        <div className="footer-col">
          <h4>pagini utile</h4>
          <ul>
            <li>
              <a href="#">Autentificare</a>
            </li>
            <li>
              <a href="#">Înregistrare</a>
            </li>
            <li>
              <a href="#">Despre noi</a>
            </li>
            <li>
              <a href="#">Cum recilez?</a>
            </li>
          </ul>
        </div>

        <div className="footer-col">
          <h4>urmărește-ne</h4>
          <div className="social-links">
            <a href="https://www.facebook.com/Greenpanion-106680575518068">
              <i>
                <FacebookOutlined />
              </i>
            </a>
            <a href="https://www.instagram.com/greenpanion/">
              <i>
                {" "}
                <InstagramOutlined />
              </i>
            </a>
            <a href="#">
              <i>
                <AndroidOutlined />
              </i>
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Footer;
