CREATE OR REPLACE FUNCTION account_statement(account_number_param INT)
    RETURNS TABLE
            (
                account_number            INT,
                operation_date            TIMESTAMP,
                reference                 VARCHAR,
                amount                    DOUBLE PRECISION,
                balance_after_transaction DOUBLE PRECISION,
                operation_motive          VARCHAR
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT a.account_number,
               t.transfer_datetime AS operation_date,
               t.reference,
               CASE
                   WHEN t.id_account_source = a.id THEN -t.amount
                   ELSE t.amount
                   END             AS amount,
               b.main_balance      AS balance_after_transaction,
               t.reason            AS operation_motive
        FROM account a
                 JOIN
             transfer t ON a.id = t.id_account_source OR a.id = t.id_account_destination
                 JOIN
             balance b ON a.id = b.id_account AND t.transfer_datetime = b.balance_date
        WHERE a.id = account_number_param
        ORDER BY t.transfer_datetime DESC;
END;
$$ LANGUAGE plpgsql;
