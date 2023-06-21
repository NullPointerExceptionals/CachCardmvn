import React, { useState } from "react";
import { CardsView } from "./CardsView";
import fetchCardsData from "../util/fetchCards";

export const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [cardsData, setCardsData] = useState([]);
  const [loginRender, setLoginRender] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);

  async function handleSubmit(e) {
    try {
      e.preventDefault();
      await fetchCardsData(username, setCardsData);
      setLoginRender(true);
    } catch (err) {
      setErrorMessage(true);
    }
  }

  return (
    <>
      {loginRender ? (
        <CardsView
          cardsData={cardsData}
          setLoginRender={setLoginRender}
          setCardsData={setCardsData}
        />
      ) : (
        <div className="login">
          <h1>Login</h1>
          <form>
            <input
              className="username"
              aria-label="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter your username"
            ></input>
            {/*Password style input for demo purposes */}
            <input
              type="text"
              className="hideText"
              placeholder="Enter your password"
            ></input>
            <br></br>
            <button onClick={handleSubmit}>Take me to my cards</button>
          </form>
          {errorMessage ? (
            <p className="loginError">username or password incorrect</p>
          ) : null}
        </div>
      )}
    </>
  );
};
