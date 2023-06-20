import React, { useState } from "react";
import { CardsView } from "./CardsView";

export const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [cardsData, setCardsData] = useState([]);
  const [loginRender, setLoginRender] = useState(false);

  async function handleSubmit(e) {
    e.preventDefault();

    console.log(username);

    const response = await fetch(
      `http://localhost:8080/cashcards/owner/${username}`
    );

    const data = await response.json();

    setCardsData(data);
    console.log(data);
    console.log("cardsData:" + cardsData);
    setLoginRender(true);
  }

  return (
    <>
      {loginRender ? (
        <CardsView cardsData={cardsData} />
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
            <br></br>
            <button onClick={handleSubmit}>Take me to my cards</button>
          </form>
        </div>
      )}
    </>
  );
};
