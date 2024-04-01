#!/bin/bash


psql -U prog_admin -h localhost -f V0__create_db.sql
find . -name "V[1-6]__*.sql" -exec psql -U prog_admin -h localhost -d my_bank -f {} \;