import React, { useState, useEffect } from "react";
import fetchAuthUser from "../util/fetchAuthUser";

export const CardLinks = ({ card: { id, owner, amount }, renderCard }) => {
  const [authUser, setAuthUser] = useState({});

  useEffect(() => {
    fetchAuthUser(id, setAuthUser);
  }, []);

  return (
    <>
      <div className="cardBody" onClick={() => renderCard(id)}>
        <p className="authUserName">{authUser.name}'s Card</p>
        <p className="ammount">£{amount}</p>
        <p className="cardNumber">5784 XXXX XXXX 000{id}</p>
      </div>
    </>
  );
};
