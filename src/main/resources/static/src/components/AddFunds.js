import React, { useState } from "react";
import fetchCardsData from "../util/fetchCards";

export const AddFunds = ({
  id,
  owner,
  setAddFundsRender,
  setAddConfirmation,
  setCardsData,
  setSingleCardRender,
}) => {
  const [amount, setAmount] = useState();

  async function putAmount() {
    const newAmount = Number(amount);
    const payload = {
      id: id,
      amount: newAmount,
      owner: owner,
    };

    await fetch(`http://localhost:8080/cashcards/owner/${owner}/${id}`, {
      method: "PUT",
      headers: { "content-type": "application/json" },
      body: JSON.stringify(payload),
    });
    setAddFundsRender(false);
    setAddConfirmation(true);
    fetchCardsData(owner, setCardsData);
    setSingleCardRender(false);
  }

  return (
    <div className="addFunds">
      <p>How much would you like to add to the card?</p>
      <input
        type="number"
        placeholder="Enter amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      ></input>
      <button onClick={putAmount}>Confirm</button>
      <button onClick={() => setAddFundsRender(false)}>Cancel</button>
    </div>
  );
};
