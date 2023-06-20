import React from "react";

export const CardLinks = ({ cardsData: { id, owner, amount } }) => {
  return (
    <>
      <h3>
        <div className="cards">
          <div className="cardsList">
            <div className="Owner">{owner}</div>
            <div className="Owner">£{amount}</div>
            <div className="Owner">{id}</div>
          </div>
        </div>
      </h3>
    </>
  );
};
