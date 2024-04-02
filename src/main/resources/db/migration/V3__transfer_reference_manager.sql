CREATE OR REPLACE FUNCTION generate_transfer_ref(account_id INT) RETURNS VARCHAR AS $$
DECLARE
    today_date DATE;
    ref_count INT;
    new_ref VARCHAR(50);
BEGIN
    today_date := CURRENT_DATE;
    SELECT COUNT(*) INTO ref_count FROM transfer t WHERE DATE(t.transfer_datetime) = today_date AND t.id_account_owner = account_id;
    ref_count := ref_count + 1;
    new_ref := 'VIR_' || TO_CHAR(today_date, 'YYYY_MM_DD') || '_' || LPAD(ref_count::text, 2, '0');

    RETURN new_ref;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION before_insert_transfer() RETURNS TRIGGER AS $$
BEGIN
    NEW.reference := generate_transfer_ref(NEW.id_account_owner);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_transfer_trigger
    BEFORE INSERT ON transfer
    FOR EACH ROW
EXECUTE FUNCTION before_insert_transfer();
