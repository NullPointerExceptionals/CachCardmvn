import React from "react";
import fetchCardsData from "../util/fetchCards";

export const DeleteCard = ({
  id,
  owner,
  setDeleteCardRender,
  setSingleCardRender,
  setCardsData,
}) => {
  const deleteCard = async () => {
    try {
      await fetch(`http://localhost:8080/cashcards/owner/${owner}/${id}`, {
        method: "DELETE",
      });
      fetchCardsData(owner, setCardsData).then(() => {
        setSingleCardRender(false);
      });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <p>Do you want to delete this card?</p>
      <button onClick={() => deleteCard()}>Confirm</button>
      <button onClick={() => setDeleteCardRender(false)}>Cancel</button>
    </div>
  );
};
