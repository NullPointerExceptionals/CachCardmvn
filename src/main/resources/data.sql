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

INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (1, 50.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (2, 0.0, 25.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (3, 75.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (1, 500.0, 0.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (2, 0.0, 250.0);
INSERT INTO transaction (cash_card_id, amount_added, amount_removed) VALUES (3, 750.0, 0.0);