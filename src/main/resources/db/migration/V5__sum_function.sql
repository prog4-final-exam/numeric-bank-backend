-- allows you to view the sum of amounts per category
-- category in a given period, by default by month, but can be by day.
CREATE OR REPLACE FUNCTION get_sum_amounts_by_category(
    account_id INT,
    type_category VARCHAR,
    start_date DATE,
    end_date DATE
)
    RETURNS TABLE
            (
                category     VARCHAR,
                period       VARCHAR,
                total_amount DOUBLE PRECISION
            )
AS
$$
BEGIN
    -- Determine if the period is by month or day based on the input dates
    IF end_date - start_date > 30 THEN
        RETURN QUERY
            SELECT tc.name                                 AS category,
                   CAST(TO_CHAR(t.transfer_datetime, 'YYYY-MM') AS VARCHAR) AS period,
                   SUM(t.amount)                           AS total_amount
            FROM transfer t
                     JOIN
                 transfer_category tc ON t.id_transfer = tc.id_transfer
            WHERE t.id_account_owner = account_id
              AND tc.category_type = type_category
              AND t.transfer_datetime BETWEEN start_date AND end_date
            GROUP BY tc.name, CAST(TO_CHAR(t.transfer_datetime, 'YYYY-MM') AS VARCHAR);
    ELSE
        RETURN QUERY
            SELECT tc.name                                    AS category,
                   CAST(TO_CHAR(t.transfer_datetime, 'YYYY-MM-DD') AS VARCHAR) AS period,
                   SUM(t.amount)                              AS total_amount
            FROM transfer t
                     JOIN
                 transfer_category tc ON t.id_transfer = tc.id_transfer
            WHERE t.id_account_owner = account_id
              AND tc.category_type = type_category
              AND t.transfer_datetime BETWEEN start_date AND end_date
            GROUP BY tc.name, CAST(TO_CHAR(t.transfer_datetime, 'YYYY-MM-DD') AS VARCHAR);
    END IF;
END;
$$
    LANGUAGE plpgsql;

-----------------------------------------
-- the sum of receipts and expenses over a given period, by default by month, but
-- month, but can be in days.
CREATE OR REPLACE FUNCTION get_sum_receipts_expenses(start_date DATE, end_date DATE, interval_type VARCHAR DEFAULT 'month')
    RETURNS TABLE
            (
                period         VARCHAR,
                total_receipts DOUBLE PRECISION,
                total_expenses DOUBLE PRECISION
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT CAST(TO_CHAR(transaction_datetime,
                       CASE
                           WHEN interval_type = 'month' THEN 'YYYY-MM'
                           WHEN interval_type = 'day' THEN 'YYYY-MM-DD'
                           ELSE 'YYYY-MM'
                           END) AS VARCHAR)                                 AS period,
               SUM(CASE WHEN amount > 0 THEN amount ELSE 0 END)  AS total_receipts,
               SUM(CASE WHEN amount < 0 THEN -amount ELSE 0 END) AS total_expenses
        FROM transaction
        WHERE transaction_datetime BETWEEN start_date AND end_date
        GROUP BY period
        ORDER BY period;
END;
$$
    LANGUAGE plpgsql;
