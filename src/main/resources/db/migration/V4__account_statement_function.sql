CREATE OR REPLACE FUNCTION get_account_statement(account_id
INT) RETURNS TABLE(date DATE, reference VARCHAR, reason 
VARCHAR, credit_amount DOUBLE PRECISION, debit_amount DOUBLE 
PRECISION, balance DOUBLE PRECISION) AS 
$$
BEGIN
	RETURN QUERY
	SELECT
	    DATE(t.transfer_datetime) AS date,
	    t.reference,
	    t.reason,
	    CASE
	        WHEN t.id_account_source = a.id THEN 0
	        ELSE t.amount
	    END AS credit_amount,
	    CASE
	        WHEN t.id_account_source = a.id THEN t.amount
	        ELSE 0
	    END AS debit_amount,
	    b.main_balance AS balance
	FROM
	    account a
	    JOIN transfer t ON a.id = t.id_account_source
	    JOIN balance b ON a.id = b.id_account
	WHERE
	    a.id = account_id
	ORDER BY DATE(t.transfer_datetime) DESC;
END;
$$
LANGUAGE plpgsql; 