-- V1 - CREATE ENUMS FOR CREDIT CARD LENDING (REVOLVING)

-- cc_revolving_line -> revolve_status
CREATE TYPE revolve_status AS ENUM (
    'ACTIVE',
    'BLOCKED',
    'CLOSED',
    'DEFAULT',
    'SUSPENDED',
    'OVERLIMIT'
);

-- cc_transaction -> transaction_type
CREATE TYPE transaction_type AS ENUM (
    'PURCHASE',
    'CASH_ADVANCE',
    'FEE',
    'INTEREST',
    'REFUND',
    'CREDIT',
    'PAYMENT'
);

-- cc_transaction -> transaction_status
CREATE TYPE transaction_status AS ENUM (
    'AUTHORIZED',
    'POSTED',
    'REVERSED',
    'DECLINED',
    'PENDING'
);

-- currency_code (commonly used)
CREATE TYPE currency_code AS ENUM (
    'EUR',
    'USD',
    'GBP',
    'CHF'
);