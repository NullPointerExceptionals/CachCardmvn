import React, { useState } from "react";
import { CardsList } from "./CardsList";

export const CardsView = ({ cardsData }) => {
  return (
    <div>
      <h1>{cardsData[0].owner}'s Cash Cards</h1>
      <CardsList cardsData={cardsData} />
    </div>
  );
};
