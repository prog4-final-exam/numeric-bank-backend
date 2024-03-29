CREATE TABLE IF NOT EXISTS account
(
    id                serial PRIMARY KEY,
    firstname        VARCHAR(100)        NOT NULL,
    lastname         VARCHAR(100)        NOT NULL,
    birthdate        DATE CHECK (birthdate <= CURRENT_DATE - INTERVAL '21 YEAR'),
    net_monthly_pay   DOUBLE PRECISION    NOT NULL,
    account_number    VARCHAR(100) UNIQUE NOT NULL,
    overdraft_allowed BOOLEAN DEFAULT false,
    bank_name         VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS balance
(
    id            serial PRIMARY KEY,
    id_account    INT,
    balance_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    main_balance  DOUBLE PRECISION,
    loan_amount   DOUBLE PRECISION DEFAULT 0,
    loan_interest DOUBLE PRECISION DEFAULT 0,
    FOREIGN KEY (id_account) REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS transaction
(
    id              serial PRIMARY KEY,
    id_account      INT,
    transaction_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount          DOUBLE PRECISION,
    transaction_type VARCHAR(200) NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account (id)
);



CREATE TABLE IF NOT EXISTS transfer
(
    id                                 serial PRIMARY KEY,
    id_account_source                  INT,
    id_account_destination             INT,
    transfer_datetime                      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    value_datetime                         TIMESTAMP,
    amount                             double precision not null,
    reason                             VARCHAR(200)     not null,
    label                              varchar(200),
    status                             VARCHAR(200),
    reference                          VARCHAR(200) unique not null,
    FOREIGN KEY (id_account_source) REFERENCES account (id),
    FOREIGN KEY (id_account_destination) REFERENCES account (id)
);


CREATE TABLE IF NOT EXISTS transfer_category
(
    id   serial PRIMARY KEY,
    name VARCHAR(100) not null,
    type VARCHAR(200) NOT NULL,
    comment text default null,
    id_transfer INT,
    FOREIGN KEY (id_transfer) REFERENCES transfer (id)
);
