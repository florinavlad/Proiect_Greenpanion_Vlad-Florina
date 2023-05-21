import React, { useState } from "react";
import {
  GoogleMap,
  useLoadScript,
  Marker,
  InfoWindow,
} from "@react-google-maps/api";
import "./map-prizes.css";

const libraries = ["places"];
const markers = [
  {
    id: 1,
    name: "jud. Cluj, orș. Cluj-Napoca, str. Observatorului 34",
    position: {
      lat: 46.756278,
      lng: 23.593285,
    },
  },

  {
    id: 2,
    name: "jud. CLuj, orș. Huedin, str. Bicălatu ",
    position: {
      lat: 46.758419,
      lng: 23.588459,
    },
  },
  {
    id: 3,
    name: "jud. Bistrița-Năsăud, orș. Bistrița, str. Tărpiului ",
    position: {
      lat: 47.135364,
      lng: 24.483851,
    },
  },
  {
    id: 4,
    name: "jud. Târgu-Mureș, orș. Târgu-Mureș, str. Gării",
    position: {
      lat: 46.533241,
      lng: 24.548864,
    },
  },
  {
    id: 5,
    name: "jud. Bihor, orș. Oradea, str. Traian Blajovici ",
    position: {
      lat: 47.03381,
      lng: 21.976009,
    },
  },
];

function Map() {
  const [selectedCenter, setSelectedCenter] = useState();

  const handleSelectedCenter = (marker) => {
    if (marker === selectedCenter) {
      return;
    }
    setSelectedCenter(marker);
  };

  const { isLoaded } = useLoadScript({
    googleMapsApiKey: "AIzaSyArAOEyQQutr9aJSuuCOEl8Y72hkaLg_KI",
    libraries,
  });

  if (!isLoaded) return <div>Se încarcă...</div>;

  return (
    <div className="map-intro">
      <h1>
        <span>Hartă</span> centre de reciclare
      </h1>

      <GoogleMap
        zoom={6}
        center={{ lat: 46, lng: 25 }}
        onClick={() => setSelectedCenter(null)}
        mapContainerClassName="map-container"
      >
        {markers.map(({ id, name, position }) => (
          <Marker
            key={id}
            position={position}
            onClick={() => handleSelectedCenter(id)}
          >
            {selectedCenter === id ? (
              <InfoWindow onCloseClick={() => setSelectedCenter(null)}>
                <div>{name}</div>
              </InfoWindow>
            ) : null}
          </Marker>
        ))}
      </GoogleMap>
    </div>
  );
}
export default Map;
