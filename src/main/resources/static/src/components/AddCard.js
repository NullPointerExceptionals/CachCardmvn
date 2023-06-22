import React, { useState } from "react";
import fetchCardsData from "../util/fetchCards";

export const AddCard = ({
  cardsData,
  setCardsData,
  setAddCardRender,
  setSingleCardRender,
}) => {
  const [newAmount, setNewAmount] = useState();
  const [newName, setNewName] = useState("");
  const owner = cardsData[0].owner;

  async function newCard() {
    const finalAmount = Number(newAmount);
    const cardPayload = {
      amount: finalAmount,
      owner: owner,
    };

    const response = await fetch(`http://localhost:8080/cashcards`, {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify(cardPayload),
    });
    const data = await response.json();
    const newCardId = data.id;

    const authUserPayload = {
      name: newName,
      cashCardId: newCardId,
    };

    await fetch(`http://localhost:8080/cashcards/authuser`, {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify(authUserPayload),
    });

    setAddCardRender(false);
    fetchCardsData(owner, setCardsData);
    setSingleCardRender(false);
  }

  return (
    <div>
      <p>{owner}</p>
      <input
        type="number"
        placeholder="Enter amount"
        value={newAmount}
        onChange={(e) => {
          setNewAmount(e.target.value);
        }}
      ></input>
      <input
        type="text"
        placeholder="Enter auth user name"
        value={newName}
        onChange={(e) => {
          setNewName(e.target.value);
        }}
      ></input>
      <button onClick={newCard}>Confirm</button>
      <button onClick={() => setAddCardRender(false)}>Cancel</button>
    </div>
  );
};
