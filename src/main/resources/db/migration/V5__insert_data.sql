INSERT INTO account (firstname, lastname, birthdate, net_monthly_pay, overdraft_allowed)
VALUES ('Jean', 'Dupont', DATE '1980-01-01', 3500, false),
     ('Marie', 'Durand', DATE '1982-02-02', 4500, true),
     ('Pierre', 'Martin', DATE '1975-03-03', 3000, false),
     ('Julie', 'Lemoine', DATE '1985-04-04', 4000, true),
     ('Luc', 'Bernard', DATE '1978-05-05', 5000, false),
     ('Sophie', 'Petit', DATE '1980-06-06', 3500, true),
     ('Nicolas', 'Roux', DATE '1982-07-07', 4500, false),
     ('Camille', 'Moreau', DATE '1979-08-08', 4000, true),
     ('Alexandre', 'Lefevre', DATE '1981-09-09', 4500, false),
     ('Sarah', 'Garnier', DATE '1983-10-10', 5000, true);

INSERT INTO balance (id_account, main_balance, loan_amount, loan_interest)
SELECT *
FROM (
        VALUES (1, 3500, 0, 0), (2, 4500, 500, 5), (3, 3000, 0, 0), (4, 4000, 400, 4), (5, 5000, 0, 0), (6, 3500, 350, 3.5), (7, 4500, 0, 0), (8, 4000, 400, 4), (9, 4500, 0, 0), (10, 5000, 500, 5)
    ) AS new_balance (
        id_account, main_balance, loan_amount, loan_interest
    )
WHERE
    NOT EXISTS (
        SELECT 1
        FROM balance
        WHERE
            balance.id_account = new_balance.id_account
    );

INSERT INTO
    transaction (
        id_account, amount, transaction_type
    )
SELECT *
FROM (
        VALUES (1, 100, 'CREDIT'), (2, 500, 'DEBIT'), (3, 200, 'CREDIT'), (4, 400, 'DEBIT'), (5, 300, 'CREDIT'), (6, 350, 'DEBIT'), (7, 400, 'CREDIT'), (8, 400, 'DEBIT'), (9, 450, 'CREDIT'), (10, 500, 'DEBIT'), (1, 150, 'CREDIT'), (1, 500, 'DEBIT'), (2, 200, 'CREDIT'), (2, 400, 'DEBIT'), (3, 300, 'CREDIT'), (3, 350, 'DEBIT'), (4, 400, 'CREDIT'), (4, 400, 'DEBIT'), (5, 450, 'CREDIT'), (5, 500, 'DEBIT'), (6, 100, 'CREDIT'), (6, 500, 'DEBIT'), (7, 200, 'CREDIT'), (7, 400, 'DEBIT'), (8, 300, 'CREDIT'), (8, 350, 'DEBIT'), (9, 400, 'CREDIT'), (9, 400, 'DEBIT'), (10, 450, 'CREDIT'), (10, 500, 'DEBIT')
    ) AS new_transaction (
        id_account, amount, transaction_type
    )
WHERE
    NOT EXISTS (
        SELECT 1
        FROM transaction
        WHERE
            transaction.id_account = new_transaction.id_account
    );

INSERT INTO
    transfer (
        id_account_source, destination_account_number, transfer_datetime, value_datetime, amount, reason, label, status, reference, is_external_bank
    )
SELECT *
FROM (
        VALUES (
                1, 'FR7630006000011234567890189', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 100, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_1', false
            ), (
                2, 'FR7630007000021234567890123', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 200, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_2', true
            ), (
                3, 'FR7630008000031234567890156', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 300, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_3', false
            ), (
                4, 'FR7630009000041234567890187', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 400, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_4', true
            ), (
                5, 'FR7630010000051234567890145', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 500, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_5', false
            ), (
                6, 'FR7630011000061234567890178', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 600, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_6', true
            ), (
                7, 'FR7630012000071234567890190', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 700, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_7', false
            ), (
                8, 'FR7630013000081234567890122', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 800, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_8', true
            ), (
                9, 'FR7630014000091234567890154', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 900, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_9', false
            ), (
                10, 'FR7630004000031234567890143', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 1000, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_10', true
            ), (
                1, 'FR7630006000011234567890189', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 200, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_11', false
            ), (
                2, 'FR7630007000021234567890123', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 300, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_12', true
            ), (
                3, 'FR7630008000031234567890156', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 450, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_13', false
            ), (
                4, 'FR7630009000041234567890187', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 350, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_14', true
            ), (
                5, 'FR7630010000051234567890145', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 550, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_15', false
            ), (
                6, 'FR7630011000061234567890178', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 670, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_16', true
            ), (
                7, 'FR7630012000071234567890190', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 7000, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_17', false
            ), (
                8, 'FR7630013000081234567890122', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 850, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_18', true
            ), (
                9, 'FR7630014000091234567890154', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-19 00:00:00', 920, 'CREDIT', 'Transfer to internal account', 'COMPLETED', 'VIR_2024_03_19_19', false
            ), (
                10, 'FR7630004000031234567890143', TIMESTAMP '2024-03-19 00:00:00', TIMESTAMP '2024-03-22 00:00:00', 1200, 'DEBIT', 'Transfer to external account', 'PENDING', 'VIR_2024_03_19_20', true
            )
    ) AS new_transfer (
        id_account_source, destination_account_number, transfer_datetime, value_datetime, amount, reason, label, status, reference, is_external_bank
    )
WHERE
    NOT EXISTS (
        SELECT 1
        FROM transfer
        WHERE
            transfer.reference = new_transfer.reference
    );

INSERT INTO
    transfer_category (
        name, category_type, id_transfer, comment
    )
SELECT *
FROM (
        VALUES ('Salary', 'INCOME', 1, NULL), ('Rent', 'EXPENSE', 2, NULL), (
                'Freelance', 'INCOME', 3, NULL
            ), (
                'Groceries', 'EXPENSE', 4, NULL
            ), (
                'Dividends', 'INCOME', 5, NULL
            ), (
                'Utilities', 'EXPENSE', 6, NULL
            ), ('Sale', 'INCOME', 7, NULL), (
                'Transport', 'EXPENSE', 8, NULL
            ), (
                'Other', 'INCOME', 9, 'Other income sources'
            ), (
                'Other', 'EXPENSE', 10, 'Other expenses'
            )
    ) AS new_transfer_category (
        name, type, id_transfer, comment
    )
WHERE
    NOT EXISTS (
        SELECT 1
        FROM transfer_category
        WHERE
            transfer_category.id_transfer = new_transfer_category.id_transfer
    );