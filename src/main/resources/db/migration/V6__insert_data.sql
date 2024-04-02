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
VALUES
(1, 3500, 0, 0), (2, 4500, 500, 5), (3, 3000, 0, 0),
(4, 4000, 400, 4), (5, 5000, 0, 0), (6, 3500, 350, 3.5),
(7, 4500, 0, 0), (8, 4000, 400, 4), (9, 4500, 0, 0),
(10, 5000, 500, 5);

INSERT INTO transaction (
    id_account, amount, transaction_type
)
VALUES
(1, 100, 'CREDIT'), (2, 500, 'DEBIT'), (3, 200, 'CREDIT'),
(4, 400, 'DEBIT'), (5, 300, 'CREDIT'), (6, 350, 'DEBIT'),
(7, 400, 'CREDIT'), (8, 400, 'DEBIT'), (9, 450, 'CREDIT'),
(10, 500, 'DEBIT'),
(1, 150, 'CREDIT'),(1, 500, 'DEBIT'),
(2, 200, 'CREDIT'), (2, 400, 'DEBIT'),
(3, 300, 'CREDIT'), (3, 350, 'DEBIT'),
(4, 400, 'CREDIT'), (4, 400, 'DEBIT'),
(5, 450, 'CREDIT'), (5, 500, 'DEBIT'),
(6, 100, 'CREDIT'), (6, 500, 'DEBIT'),
(7, 200, 'CREDIT'), (7, 400, 'DEBIT'),
(8, 300, 'CREDIT'), (8, 350, 'DEBIT'),
(9, 400, 'CREDIT'), (9, 400, 'DEBIT'),
(10, 450, 'CREDIT'), (10, 500, 'DEBIT')

;

INSERT INTO transfer (
    id_account_source, destination_account_number, amount, reason, label, status, is_external_bank
)
VALUES (
    1, 'FR7630006000011234567890189', 100, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    2, 'FR7630007000021234567890123', 200, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    3, 'FR7630008000031234567890156', 300, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    4, 'FR7630009000041234567890187', 400, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    5, 'FR7630010000051234567890145', 500, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    6, 'FR7630011000061234567890178', 600, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    7, 'FR7630012000071234567890190', 700, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    8, 'FR7630013000081234567890122', 800, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    9, 'FR7630014000091234567890154', 900, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    10, 'FR7630004000031234567890143', 1000, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    1, 'FR7630006000011234567890189', 200, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    2, 'FR7630007000021234567890123', 300, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    3, 'FR7630008000031234567890156', 450, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    4, 'FR7630009000041234567890187', 350, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    5, 'FR7630010000051234567890145', 550, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    6, 'FR7630011000061234567890178', 670, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    7, 'FR7630012000071234567890190', 7000, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    8, 'FR7630013000081234567890122', 850, 'DEBIT', 'Transfer to external account', 'PENDING', true
), (
    9, 'FR7630014000091234567890154', 920, 'CREDIT', 'Transfer to internal account', 'COMPLETED', false
), (
    10, 'FR7630004000031234567890143', 1200, 'DEBIT', 'Transfer to external account', 'PENDING', true
);

INSERT INTO
    category (
    category_name, category_type
)
VALUES
('Others', 'EITHER'),
('Food & Drinks', 'EXPENSE'),
('Shopping', 'EXPENSE'),
('Housing', 'EITHER'),
('Transportation', 'EXPENSE'),
('Vehicle', 'EITHER'),
('Communication, PC', 'EITHER'),
('Financial expenses', 'EXPENSE'),
('Investments', 'INCOME'),
('Salary', 'INCOME');

INSERT INTO transaction_have(id_transaction, id_category, comment)
VALUES
(2, 1, 'Entertainment'),
(7, 10, null),
(5, 4, null),
(12, 2, null);