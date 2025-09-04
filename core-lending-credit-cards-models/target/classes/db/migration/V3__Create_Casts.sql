-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL ENUM TYPES

-------------------------
-- revolve_status
-------------------------
CREATE CAST (varchar AS revolve_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- transaction_type
-------------------------
CREATE CAST (varchar AS transaction_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- transaction_status
-------------------------
CREATE CAST (varchar AS transaction_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- currency_code
-------------------------
CREATE CAST (varchar AS currency_code)
    WITH INOUT
    AS IMPLICIT;
