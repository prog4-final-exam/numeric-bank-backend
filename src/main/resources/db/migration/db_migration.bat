psql -U postgres -f V0__create_db.sql
for /R %%i IN (V1__init_db.sql, V2__insert_data.sql, V3__sum_function.sql, V4__account_statement_function.sql) DO psql -U postgres -d my_bank -f %%i