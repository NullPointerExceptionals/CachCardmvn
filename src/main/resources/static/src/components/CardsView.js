import React, { useState } from "react";
import { CardsList } from "./CardsList";
import { SingleCard } from "./SingleCard";

export const CardsView = ({ cardsData, setLoginRender, setCardsData }) => {
  const [singleCardRender, setSingleCardRender] = useState(false);
  const [singleCard, setSingleCard] = useState({});

  async function renderCard(id) {
    try {
      const response = await fetch(
        `http://localhost:8080/cashcards/owner/${cardsData[0].owner}/${id}`
      );
      const cardData = await response.json();

      setSingleCard(cardData);
      setSingleCardRender(true);
    } catch (err) {
      console.log("Oh no an error! ", err);
    }
  }

  return (
    <div>
      <h2 className="ownerTitle">{cardsData[0].owner}'s Cash Cards</h2>
      <button onClick={() => setLoginRender(false)}>Log Out</button>
      <button>Add Card</button>

      <div className="cardsContainer">
        {singleCardRender ? (
          <SingleCard
            singleCard={singleCard}
            setSingleCardRender={setSingleCardRender}
            setCardsData={setCardsData}
          />
        ) : (
          <CardsList cardsData={cardsData} renderCard={renderCard} />
        )}
      </div>
    </div>
  );
};
