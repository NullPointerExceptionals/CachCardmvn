import React, { useState } from "react";
import { CardLinks } from "./CardLinks";

export const CardsList = ({ cardsData }) => {
  return (
    <>
      <div className="cards">
        {/* {cardsData.map((card, idx) => {
          return <CardLinks card={card} key={idx} />;
        })} */}
        <p>{cardsData[0].amount}</p>
      </div>
    </>
  );
};
