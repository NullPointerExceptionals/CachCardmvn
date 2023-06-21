import React, { useState, useEffect } from "react";
import fetchAuthUser from "../util/fetchAuthUser";
import { Transactions } from "./Transactions";
import { DeleteCard } from "./DeleteCard";

export const SingleCard = ({
  singleCard: { id, owner, amount },
  setSingleCardRender,
  setCardsData,
}) => {
  const [authUser, setAuthUser] = useState({});
  const [deleteCardRender, setDeleteCardRender] = useState(false);

  useEffect(() => {
    fetchAuthUser(id, setAuthUser);
  }, []);

  return (
    <>
      <div className="cardBodySingle singleCard">
        <p className="authUserName">{authUser.name}'s Card</p>
        <p className="ammount">£{amount}</p>
        <p className="cardNumber">5784 XXXX XXXX 000{id}</p>
      </div>
      <div className="flex">
        <button onClick={() => setSingleCardRender(false)}>
          Back to Cards
        </button>
        <button>Add Funds</button>
        <button onClick={() => setDeleteCardRender(true)}>Delete Card</button>
      </div>
      {deleteCardRender ? (
        <DeleteCard
          id={id}
          owner={owner}
          setDeleteCardRender={setDeleteCardRender}
          setSingleCardRender={setSingleCardRender}
          setCardsData={setCardsData}
        />
      ) : null}
      <Transactions id={id} />
    </>
  );
};
