#!/bin/bash

psql -U postgres -f V0__create_db.sql
find . -name "V[1-4]__*.sql" -exec psql -U postgres -d my_bank -f {} \;