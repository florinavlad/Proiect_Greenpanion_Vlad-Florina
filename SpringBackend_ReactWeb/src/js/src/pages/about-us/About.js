import React from "react";
import "./About.css";

const About = () => {
  return (
    <div>
      <div className="step-intro" id="about">
        <h4>
          Cum poți contribui? <br />
          Descoperă <span>Greenpanion</span> în doar 4 pași simpli.
        </h4>
      </div>

      <div className="container">
        <div className="card">
          <div className="box">
            <div className="card-content">
              <h5>01</h5>
              <h6>Primul pas</h6>
              <p>
                Navighează pe site-ul nostru, crează-ți propriul cont și
                urmărește-ne și în aplicație pentru mai multe surprize.
              </p>
              <a href="#">Înregistrare</a>
            </div>
          </div>
        </div>

        <div className="card">
          <div className="box">
            <div className="card-content">
              <h5>02</h5>
              <h6>Pasul doi</h6>
              <p>
                Descarcă aplicația mobilă cu posibilitatea de a câștiga premii
                prin scanarea codului de la centrele de reciclare.
              </p>
              <a href="#">App</a>
            </div>
          </div>
        </div>

        <div className="card">
          <div className="box">
            <div className="card-content">
              <h5>03</h5>
              <h6>Pasul trei</h6>
              <p>
                Crește în nivel acumulând puncte, deoarece premiile așteaptă !
                Găsește cel mai apropiat centru de reciclare.
              </p>
              <a href="#">Map and Prizes</a>
            </div>
          </div>
        </div>
        <div className="card">
          <div className="box">
            <div className="card-content">
              <h5>04</h5>
              <h6>Pasul patru</h6>
              <p>
                Vorbește cu ceilalți utilizatori prin aplicația Greenpanion,
                învățați să reciclați împreună și creșteți în ranking.
              </p>
              <a href="#">Cum reciclez?</a>
            </div>
          </div>
        </div>
      </div>

      <div className="container1">
        <div className="infocard">
          <div className="left">
            <h3>Cine suntem?</h3>
          </div>
          <div className="right">
            <p>
              <span>Greenpanion</span> este cu adevărat un companion pentru
              reciclare. Gândindu-ne la toate modurile în care am putea ajuta
              mediul înconjurător, am decis să contribuim la construirea unei
              lumi mai sustenabile motivând populația să sorteze materia primă,
              urmând pașii de mai sus cu ajutorul aplicației mobile Greenpanio
              Premiile, punctele acumulate și conexiunea cu ceilalți utilizatori
              sunt doar câteva dintre motivele pentru care trebuie să te
              grăbești spre centrele de reciclare din apropierea ta, să
              introduci materialele în automate și să scanezi prin aplicație
              codul de bare.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
export default About;
