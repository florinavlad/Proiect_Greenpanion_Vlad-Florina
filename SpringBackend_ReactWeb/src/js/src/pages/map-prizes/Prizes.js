import React, { useState } from "react";
import "./map-prizes.css";
import bike from "../../assets/Prizes/bike.jpg";
import tent from "../../assets/Prizes/tent.jpg";
import { LeftOutlined, RightOutlined } from "@ant-design/icons";

const prizes = [
  {
    title: "Tent and Hammock",
    subtitle:
      "A 8 person tent for you and your friends to enjoy the great outdoors. All you have to do is reach level 5 in the app.",
    img: tent,
  },
  {
    title: "Bike subscription",
    subtitle:
      "Free 3 month subscription to Cluj Bike. Stay green by staying out of traffic and get out into the fresh air. All you have to do is reach level 15 in the app. ",
    img: bike,
  },
];

function Prizes() {
  const [activePrizeImg, setActivePrizeImg] = useState(0);
  return (
    <div className="prize">
      <div
        className="prize-container"
        style={{ backgroundImage: `url(${prizes[activePrizeImg].img})` }}
      >
        <div
          className="prev"
          onClick={() => {
            activePrizeImg > 0 && setActivePrizeImg(activePrizeImg - 1);
          }}
        >
          <LeftOutlined
            style={{ fontSize: 30, color: "whitesmoke", opacity: 0.5 }}
          />
        </div>
        <div className="prize-info">
          <p>{prizes[activePrizeImg].subtitle}</p>
          <h1>{prizes[activePrizeImg].title}</h1>
        </div>
        <div
          className="next"
          onClick={() => {
            activePrizeImg < prizes.length - 1 &&
              setActivePrizeImg(activePrizeImg + 1);
          }}
        >
          <RightOutlined
            style={{ fontSize: 30, color: "whitesmoke", opacity: 0.5 }}
          />
        </div>
      </div>
    </div>
  );
}
export default Prizes;
