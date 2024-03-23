INSERT INTO account (id, firstname, lastname, birthdate, net_monthly_pay, account_number, overdraft_allowed)
SELECT *
FROM (VALUES (1, 'Jean', 'Dupont', '1980-01-01', 3500, 'FR7630004000031234567890143', false),
             (2, 'Marie', 'Durand', '1982-02-02', 4500, 'FR7630006000011234567890189', true),
             (3, 'Pierre', 'Martin', '1975-03-03', 3000, 'FR7630007000021234567890123', false),
             (4, 'Julie', 'Lemoine', '1985-04-04', 4000, 'FR7630008000031234567890156', true),
             (5, 'Luc', 'Bernard', '1978-05-05', 5000, 'FR7630009000041234567890187', false),
             (6, 'Sophie', 'Petit', '1980-06-06', 3500, 'FR7630010000051234567890145', true),
             (7, 'Nicolas', 'Roux', '1982-07-07', 4500, 'FR7630011000061234567890178', false),
             (8, 'Camille', 'Moreau', '1979-08-08', 4000, 'FR7630012000071234567890190', true),
             (9, 'Alexandre', 'Lefevre', '1981-09-09', 4500, 'FR7630013000081234567890122', false),
             (10, 'Sarah', 'Garnier', '1983-10-10', 5000, 'FR7630014000091234567890154', true)) AS new_account(id,
                                                                                                               firstname,
                                                                                                               lastname,
                                                                                                               birthdate,
                                                                                                               net_monthly_pay,
                                                                                                               account_number,
                                                                                                               overdraft_allowed)
WHERE NOT EXISTS (SELECT 1 FROM account WHERE account.account_number = new_account.account_number);


INSERT INTO balance (id_account, main_balance, loan_amount, loan_interest)
SELECT *
FROM (VALUES (1, 3500, 0, 0),
             (2, 4500, 500, 5),
             (3, 3000, 0, 0),
             (4, 4000, 400, 4),
             (5, 5000, 0, 0),
             (6, 3500, 350, 3.5),
             (7, 4500, 0, 0),
             (8, 4000, 400, 4),
             (9, 4500, 0, 0),
             (10, 5000, 500, 5)) AS new_balance(id_account, main_balance, loan_amount, loan_interest)
WHERE NOT EXISTS (SELECT 1 FROM balance WHERE balance.id_account = new_balance.id_account);


INSERT INTO transaction (id_account, amount, transaction_type)
SELECT *
FROM (VALUES (1, 100, 'CREDIT'),
             (2, 500, 'DEBIT'),
             (3, 200, 'CREDIT'),
             (4, 400, 'DEBIT'),
             (5, 300, 'CREDIT'),
             (6, 350, 'DEBIT'),
             (7, 400, 'CREDIT'),
             (8, 400, 'DEBIT'),
             (9, 450, 'CREDIT'),
             (10, 500, 'DEBIT')) AS new_transaction(id_account, amount, transaction_type)
WHERE NOT EXISTS (SELECT 1 FROM transaction WHERE transaction.id_account = new_transaction.id_account);


INSERT INTO transfer (id_account_source, id_account_destination, value_datetime, amount, transaction_type, reference,
                      is_external_bank)
SELECT *
FROM (VALUES (1, 2, '2024-03-19 00:00:00', 100, 'CREDIT', 'VIR_2024_03_19_1', false),
             (2, 1, '2024-03-22 00:00:00', 200, 'DEBIT', 'VIR_2024_03_19_2', true),
             (3, 4, '2024-03-19 00:00:00', 300, 'CREDIT', 'VIR_2024_03_19_3', false),
             (4, 3, '2024-03-22 00:00:00', 400, 'DEBIT', 'VIR_2024_03_19_4', true),
             (5, 6, '2024-03-19 00:00:00', 500, 'CREDIT', 'VIR_2024_03_19_5', false),
             (6, 5, '2024-03-22 00:00:00', 600, 'DEBIT', 'VIR_2024_03_19_6', true),
             (7, 8, '2024-03-19 00:00:00', 700, 'CREDIT', 'VIR_2024_03_19_7', false),
             (8, 7, '2024-03-22 00:00:00', 800, 'DEBIT', 'VIR_2024_03_19_8', true),
             (9, 10, '2024-03-19 00:00:00', 900, 'CREDIT', 'VIR_2024_03_19_9', false),
             (10, 9, '2024-03-22 00:00:00', 1000, 'DEBIT', 'VIR_2024_03_19_10',
              true)) AS new_transfer(id_account_source, id_account_destination, value_datetime, amount,
                                     transaction_type, reference, is_external_bank)
WHERE NOT EXISTS (SELECT 1 FROM transfer WHERE transfer.reference = new_transfer.reference);


INSERT INTO transfer_category (name, type, id_transfer)
SELECT *
FROM (VALUES ('Salary', 'INCOME', 1),
             ('Rent', 'EXPENSES', 2),
             ('Freelance', 'INCOME', 3),
             ('Groceries', 'EXPENSES', 4),
             ('Dividends', 'INCOME', 5),
             ('Utilities', 'EXPENSES', 6),
             ('Sale', 'INCOME', 7),
             ('Transport', 'EXPENSES', 8),
             ('Other', 'INCOME', 9, 'Other income sources'),
             ('Other', 'EXPENSES', 10, 'Other expenses')) AS new_transfer_category(name, type, id_transfer)
WHERE NOT EXISTS (SELECT 1
                  FROM transfer_category
                  WHERE transfer_category.id_transfer = new_transfer_category.id_transfer);