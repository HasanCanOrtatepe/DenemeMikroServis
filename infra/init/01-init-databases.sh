#!/bin/bash
# Her mikroservis icin ayri bir veritabani olusturur (database-per-service).
# Bu script sadece postgres-data volume'u BOSKEN, container ilk ayaga kalkarken calisir.
set -e

for db in productdb customerdb orderdb; do
  echo "Creating database: $db"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname postgres <<-EOSQL
    CREATE DATABASE $db;
    GRANT ALL PRIVILEGES ON DATABASE $db TO $POSTGRES_USER;
EOSQL
done
