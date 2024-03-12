CREATE TABLE IF NOT EXISTS account
(
    id                serial PRIMARY KEY,
    first_name        VARCHAR(100)        NOT NULL,
    last_name         VARCHAR(100)        NOT NULL,
    birth_date        DATE CHECK (birth_date <= CURRENT_DATE - INTERVAL '21 YEAR'),
    net_monthly_pay   DOUBLE PRECISION    NOT NULL,
    account_number    VARCHAR(100) UNIQUE NOT NULL,
    overdraft_allowed BOOLEAN DEFAULT false,
    overdraft_limit   DOUBLE PRECISION
);

-- update the overdraft_limit value each time the net_monthly_pay is updated
CREATE OR REPLACE FUNCTION update_overdraft_limit()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.overdraft_limit := NEW.net_monthly_pay / 3;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_overdraft_limit
    BEFORE UPDATE OF net_monthly_pay
    ON account
    FOR EACH ROW
EXECUTE FUNCTION update_overdraft_limit();

CREATE TABLE IF NOT EXISTS balance
(
    id            serial PRIMARY KEY,
    id_account    INT,
    balance_date  DATE,
    main_balance  DOUBLE PRECISION,
    loan_amount   DOUBLE PRECISION DEFAULT 0,
    loan_interest DOUBLE PRECISION DEFAULT 0,
    FOREIGN KEY (id_account) REFERENCES account (id)
);

-- create type enum for the credit and debit
create type category_type as enum ('CREDIT','DEBIT');

CREATE TABLE IF NOT EXISTS transaction_category
(
    id   serial PRIMARY KEY,
    name VARCHAR(100),
    type category_type
);

create table if not exists transaction_category_assignment
(
    id                      serial primary key,
    id_transaction_category int,
    comment                 text default null,
    FOREIGN KEY (id_transaction_category) REFERENCES transaction_category (id)
);


-- create type enum for transfer_type
CREATE TYPE transfer_status AS ENUM ('Pending', 'Completed', 'Cancelled');

CREATE TABLE IF NOT EXISTS transfer
(
    id                                 serial PRIMARY KEY,
    id_account_source                  INT,
    id_account_destination             INT,
    id_transaction_category_assignment int,
    transfer_date                      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    value_date                         TIMESTAMP,
    amount                             double precision not null,
    reason                             VARCHAR(200)     not null,
    status                             transfer_status,
    is_external                        BOOLEAN          not null,
    reference                          VARCHAR(200) unique,
    FOREIGN KEY (id_account_source) REFERENCES account (id),
    FOREIGN KEY (id_account_destination) REFERENCES account (id),
    FOREIGN KEY (id_transaction_category_assignment) REFERENCES transaction_category_assignment (id)
);

