import React from "react";
import { CardLinks } from "./CardLinks";

export const CardsList = ({ cardsData }) => {
  return (
    <>
      <div className="cardsList">
        {cardsData.map((card, idx) => {
          return <CardLinks card={card} key={idx} />;
        })}
      </div>
    </>
  );
};
