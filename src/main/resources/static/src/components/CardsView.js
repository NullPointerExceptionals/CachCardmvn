import React, { useState } from "react";
import { CardsList } from "./CardsList";

export const CardsView = ({ cardsData }) => {
  return (
    <div>
      <CardsList cardsData={cardsData} />
    </div>
  );
};
