CREATE OR REPLACE FUNCTION generate_account_number(new_id BIGINT) RETURNS VARCHAR AS $$
DECLARE
    prefix VARCHAR(8) := '00040003';
    suffix VARCHAR(2) := '02';
BEGIN
    RETURN prefix || LPAD(new_id::text, 11, '0') || suffix;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION before_insert_accounts() RETURNS TRIGGER AS $$
BEGIN
    NEW.account_number := generate_account_number(NEW.id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_accounts_trigger
    BEFORE INSERT ON account
    FOR EACH ROW
EXECUTE FUNCTION before_insert_accounts();
