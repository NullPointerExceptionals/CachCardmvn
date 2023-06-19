INSERT INTO cash_card (amount, owner) VALUES (100.0, 'Natalie');
INSERT INTO cash_card (amount, owner) VALUES (200.0, 'Domiique');
INSERT INTO cash_card (amount, owner) VALUES (300.0, 'Jeff');
INSERT INTO cash_card (amount, owner) VALUES (400.0, 'John');
INSERT INTO cash_card (amount, owner) VALUES (500.0, 'Tim');
INSERT INTO cash_card (amount, owner) VALUES (600.0, 'Mohamed');
INSERT INTO cash_card (amount, owner) VALUES (200.0, 'Natalie');
INSERT INTO cash_card (amount, owner) VALUES (400.0, 'Domiique');
INSERT INTO cash_card (amount, owner) VALUES (600.0, 'Jeff');
INSERT INTO cash_card (amount, owner) VALUES (800.0, 'John');
INSERT INTO cash_card (amount, owner) VALUES (1000.0, 'Tim');
INSERT INTO cash_card (amount, owner) VALUES (1200.0, 'Mohamed');


INSERT INTO auth_user (name, cash_card_id) VALUES ('Alice', 1);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Bob', 2);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Charlie', 3);
INSERT INTO auth_user (name, cash_card_id) VALUES ('David', 4);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Eve', 5);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Frank', 6);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Grace', 7);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Henry', 8);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Isabella', 9);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Jack', 10);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Kate', 11);
INSERT INTO auth_user (name, cash_card_id) VALUES ('Liam', 12);


INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (1, 25.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (1, 0.0, 15.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (1, 10.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (2, 50.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (2, 0.0, 10.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (2, 15.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (3, 30.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (3, 0.0, 20.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (3, 25.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (4, 15.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (4, 0.0, 5.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (4, 20.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (5, 40.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (5, 0.0, 10.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (5, 5.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (6, 20.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (6, 0.0, 10.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (6, 15.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (7, 25.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (7, 0.0, 15.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (7, 10.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (8, 50.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (8, 0.0, 25.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (8, 15.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (9, 10.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (9, 0.0, 5.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (9, 25.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (10, 45.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (10, 0.0, 20.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (10, 50.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (11, 30.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (11, 0.0, 30.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (11, 25.0, 0.0);

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (12, 35.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (12, 0.0, 25.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (12, 20.0, 0.0);

