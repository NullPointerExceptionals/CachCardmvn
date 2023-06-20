import React, { useState } from "react";
import { CardsView } from "./CardsView";

export const LoginPage = (props) => {
  const [username, setUsername] = useState("");
  const [cardsData, setCardsData] = useState(false);

  async function handleSubmit(e) {
    e.preventDefault();

    console.log(username);

    const response = await fetch(
      `http://localhost:8080/cashcards/owner/${username}`
    );

    const data = await response.json();
    setCardsData(data);
    console.log(data);
  }

  return (
    <>
      {cardsData ? (
        <CardsView cardData={cardsData} />
      ) : (
        <div className="login">
          <p>Login</p>
          <form>
            <input
              className="username"
              aria-label="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter your username"
            ></input>
            <button onClick={handleSubmit}>Take me to my cards</button>
          </form>
        </div>
      )}
    </>
  );
};
