psql -U postgres -f V0__create_db.sql
for /R %%i IN (V1__init_db.sql, V2__account_number_manager.sql, V3__transfer_reference_manager.sql, V4__account_statement_function.sql, V5__sum_function.sql, V6__insert_data.sql, ) DO psql -U postgres -d my_bank -f %%i
