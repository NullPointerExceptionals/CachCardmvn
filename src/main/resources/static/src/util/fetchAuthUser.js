async function fetchAuthUser(id, setAuthUser) {
  try {
    const response = await fetch(
      `http://localhost:8080/cashcards/authuser/id/${id}`
    );
    const authUserData = await response.json();
    setAuthUser(authUserData);
  } catch (err) {
    console.log("Oh no an error! ", err);
  }
}

export default fetchAuthUser;
