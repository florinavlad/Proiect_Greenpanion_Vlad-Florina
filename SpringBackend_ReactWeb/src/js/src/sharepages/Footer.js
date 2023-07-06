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
            Materialele primare reciclate sunt hârtie, plastic și metal. Scopul
            aplicației Greenpanion este de oferi ajutor oamenilor să recicleze
            într-un mod plăcut, cu recompense pe măsură.
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
            <a href="https://drive.google.com/drive/folders/1NS8sznOMfQQrdfmObNcOhZ-f477Z4JbW?usp=sharing">
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
