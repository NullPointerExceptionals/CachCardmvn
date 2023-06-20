import React from "react";

export const CardLinks = ({ card: { id, owner, amount } }) => {
  return (
    <>
      <h3>
        <div className="cardsLinks">
          <div className="Owner">{owner}</div>
          <div className="Owner">£{amount}</div>
          <div className="Owner">{id}</div>
        </div>
      </h3>
    </>
  );
};
