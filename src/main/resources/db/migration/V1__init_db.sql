CREATE TABLE IF NOT EXISTS account
(
    id                serial PRIMARY KEY,
    firstname         VARCHAR(100)        NOT NULL,
    lastname          VARCHAR(100)        NOT NULL,
    birthdate         DATE CHECK (birthdate <= CURRENT_DATE - INTERVAL '21 YEAR'),
    net_monthly_pay   DOUBLE PRECISION    NOT NULL,
    account_number    VARCHAR(100) DEFAULT '',
    overdraft_allowed BOOLEAN DEFAULT false
);


CREATE TABLE IF NOT EXISTS balance
(
    id            serial PRIMARY KEY,
    id_account    INT,
    balance_datetime  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    main_balance  DOUBLE PRECISION DEFAULT 0,
    loan_amount   DOUBLE PRECISION DEFAULT 0,
    loan_interest DOUBLE PRECISION DEFAULT 0,
    FOREIGN KEY (id_account) REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS transaction
(
    id                   serial PRIMARY KEY,
    id_account           INT,
    transaction_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount               DOUBLE PRECISION NOT NULL,
    transaction_type     VARCHAR(200) NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account (id)
);



CREATE TABLE IF NOT EXISTS transfer
(
    id                         serial PRIMARY KEY,
    id_account_source          INT,
    destination_account_number VARCHAR(200) NOT NULL,
    transfer_datetime          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    value_datetime             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount                     double precision    not null default 0,
    reason                     VARCHAR(200)        not null,
    label                      varchar(200),
    status                     VARCHAR(200),
    reference                  VARCHAR(200) DEFAULT '',
    is_external_bank           BOOLEAN             not null DEFAULT false,
    FOREIGN KEY (id_account_source) REFERENCES account (id)
);


CREATE TABLE IF NOT EXISTS category
(
    id          serial PRIMARY KEY,
    name        VARCHAR(100) not null,
    category_type        VARCHAR(200) NOT NULL,
    comment     text default null
);

CREATE TABLE IF NOT EXISTS transfer_have
(
    id serial PRIMARY KEY ,
    id_transfer int REFERENCES transfer(id),
    id_category int REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS transaction_have
(
    id serial PRIMARY KEY ,
    id_transaction int REFERENCES transaction(id),
    id_category int REFERENCES category(id)
);