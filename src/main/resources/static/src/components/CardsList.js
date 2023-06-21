import React from "react";
import { CardLinks } from "./CardLinks";

export const CardsList = ({ cardsData, renderCard }) => {
  return (
    <>
      <div className="cardsList">
        {cardsData.map((card, idx) => {
          return <CardLinks card={card} key={idx} renderCard={renderCard} />;
        })}
      </div>
    </>
  );
};
