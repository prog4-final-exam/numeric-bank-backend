CREATE OR REPLACE FUNCTION account_statement(account_number_param 
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
	        WHEN t.id_account_source = a.id THEN NULL
	        ELSE t.amount
	    END AS credit_amount,
	    CASE
	        WHEN t.id_account_source = a.id THEN t.amount
	        ELSE NULL
	    END AS debit_amount,
	    b.main_balance AS balance
	FROM
	    account a
	    JOIN transfer t ON a.id = t.id_account_source
	    JOIN balance b ON a.id = b.id_account
	WHERE
	    a.id = account_number_param
	ORDER BY DATE(t.transfer_datetime) DESC;
END;
$$
LANGUAGE plpgsql; 