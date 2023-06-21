async function fetchCardsData(username, setCardsData) {
  const response = await fetch(
    `http://localhost:8080/cashcards/owner/${username}`
  );
  const cardsData = await response.json();
  setCardsData(cardsData);
}

export default fetchCardsData;
