import React, { useState, useEffect } from "react";

export const Transactions = ({ id }) => {
  const [transactions, setTransactions] = useState([]);
  const [transactionsDate, setTransactionsDate] = useState("");
  const [transactionsTime, setTransactionsTime] = useState("");

  async function fetchTransactions(id) {
    const response = await fetch(
      `http://localhost:8080/cashcards/${id}/transactions`
    );
    const transactionsData = await response.json();
    setTransactions(transactionsData);
  }

  useEffect(() => {
    fetchTransactions(id);
  }, []);

  function renderDateTime(transaction) {
    const transactionDate = transaction.dateCreated;
    const [date, time] = transactionDate.split("T");
    const formattedDate = date.split("-").reverse().join("/");
    const formattedTime = time.slice(0, 8);
    return `${formattedDate} ${formattedTime}`;
  }

  return (
    <div className="transactions">
      <h3>Transactions</h3>
      {transactions.map((transaction, idx) => {
        return (
          <div key={idx} className="invTransactions">
            {transaction.amountAdded ? (
              <p>
                {renderDateTime(transaction)}
                <span className="transactionSpace"></span> +£
                {transaction.amountAdded}
              </p>
            ) : null}
            {transaction.amountRemoved ? (
              <p>
                {renderDateTime(transaction)}
                <span className="transactionSpace"></span> -£
                {transaction.amountRemoved}
              </p>
            ) : null}
          </div>
        );
      })}
    </div>
  );
};
