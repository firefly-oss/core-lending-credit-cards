-- V4 - CONVERT ALL ID FIELDS FROM BIGINT TO UUID
-- This migration converts all primary keys and foreign keys from BIGINT to UUID
-- while preserving existing data by generating UUIDs for existing records

-- Enable UUID extension if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- STEP 1: Add new UUID columns alongside existing BIGINT columns
-- ========================================================================

-- Add UUID columns to cc_revolving_line
ALTER TABLE cc_revolving_line 
ADD COLUMN cc_revolving_line_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN account_uuid UUID,
ADD COLUMN card_uuid UUID,
ADD COLUMN product_uuid UUID;

-- Add UUID columns to cc_billing_cycle
ALTER TABLE cc_billing_cycle 
ADD COLUMN cc_billing_cycle_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN cc_revolving_line_uuid UUID;

-- Add UUID columns to cc_statement
ALTER TABLE cc_statement 
ADD COLUMN cc_statement_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN cc_billing_cycle_uuid UUID;

-- Add UUID columns to cc_transaction
ALTER TABLE cc_transaction 
ADD COLUMN cc_transaction_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN cc_revolving_line_uuid UUID,
ADD COLUMN transaction_uuid UUID;

-- Add UUID columns to cc_payment
ALTER TABLE cc_payment 
ADD COLUMN cc_payment_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN cc_revolving_line_uuid UUID,
ADD COLUMN cc_statement_uuid UUID,
ADD COLUMN transaction_uuid UUID;

-- ========================================================================
-- STEP 2: Populate UUID foreign key relationships
-- ========================================================================

-- Update cc_billing_cycle foreign key references
UPDATE cc_billing_cycle 
SET cc_revolving_line_uuid = rl.cc_revolving_line_uuid
FROM cc_revolving_line rl 
WHERE cc_billing_cycle.cc_revolving_line_id = rl.cc_revolving_line_id;

-- Update cc_statement foreign key references
UPDATE cc_statement 
SET cc_billing_cycle_uuid = bc.cc_billing_cycle_uuid
FROM cc_billing_cycle bc 
WHERE cc_statement.cc_billing_cycle_id = bc.cc_billing_cycle_id;

-- Update cc_transaction foreign key references
UPDATE cc_transaction 
SET cc_revolving_line_uuid = rl.cc_revolving_line_uuid
FROM cc_revolving_line rl 
WHERE cc_transaction.cc_revolving_line_id = rl.cc_revolving_line_id;

-- Update cc_payment foreign key references
UPDATE cc_payment 
SET cc_revolving_line_uuid = rl.cc_revolving_line_uuid
FROM cc_revolving_line rl 
WHERE cc_payment.cc_revolving_line_id = rl.cc_revolving_line_id;

-- Update cc_payment statement references (where applicable)
UPDATE cc_payment 
SET cc_statement_uuid = st.cc_statement_uuid
FROM cc_statement st 
WHERE cc_payment.cc_statement_id = st.cc_statement_id 
AND cc_payment.cc_statement_id IS NOT NULL;

-- ========================================================================
-- STEP 3: Drop existing foreign key constraints
-- ========================================================================

-- Drop foreign key constraints
ALTER TABLE cc_billing_cycle DROP CONSTRAINT IF EXISTS fk_cycle_line;
ALTER TABLE cc_statement DROP CONSTRAINT IF EXISTS fk_statement_cycle;
ALTER TABLE cc_transaction DROP CONSTRAINT IF EXISTS fk_txn_line;
ALTER TABLE cc_payment DROP CONSTRAINT IF EXISTS fk_pay_line;

-- ========================================================================
-- STEP 4: Drop old BIGINT columns and rename UUID columns
-- ========================================================================

-- cc_revolving_line table
ALTER TABLE cc_revolving_line 
DROP COLUMN cc_revolving_line_id,
DROP COLUMN account_id,
DROP COLUMN card_id,
DROP COLUMN product_id;

ALTER TABLE cc_revolving_line 
RENAME COLUMN cc_revolving_line_uuid TO cc_revolving_line_id;
ALTER TABLE cc_revolving_line 
RENAME COLUMN account_uuid TO account_id;
ALTER TABLE cc_revolving_line 
RENAME COLUMN card_uuid TO card_id;
ALTER TABLE cc_revolving_line 
RENAME COLUMN product_uuid TO product_id;

-- cc_billing_cycle table
ALTER TABLE cc_billing_cycle 
DROP COLUMN cc_billing_cycle_id,
DROP COLUMN cc_revolving_line_id;

ALTER TABLE cc_billing_cycle 
RENAME COLUMN cc_billing_cycle_uuid TO cc_billing_cycle_id;
ALTER TABLE cc_billing_cycle 
RENAME COLUMN cc_revolving_line_uuid TO cc_revolving_line_id;

-- cc_statement table
ALTER TABLE cc_statement 
DROP COLUMN cc_statement_id,
DROP COLUMN cc_billing_cycle_id;

ALTER TABLE cc_statement 
RENAME COLUMN cc_statement_uuid TO cc_statement_id;
ALTER TABLE cc_statement 
RENAME COLUMN cc_billing_cycle_uuid TO cc_billing_cycle_id;

-- cc_transaction table
ALTER TABLE cc_transaction 
DROP COLUMN cc_transaction_id,
DROP COLUMN cc_revolving_line_id,
DROP COLUMN transaction_id;

ALTER TABLE cc_transaction 
RENAME COLUMN cc_transaction_uuid TO cc_transaction_id;
ALTER TABLE cc_transaction 
RENAME COLUMN cc_revolving_line_uuid TO cc_revolving_line_id;
ALTER TABLE cc_transaction 
RENAME COLUMN transaction_uuid TO transaction_id;

-- cc_payment table
ALTER TABLE cc_payment 
DROP COLUMN cc_payment_id,
DROP COLUMN cc_revolving_line_id,
DROP COLUMN cc_statement_id,
DROP COLUMN transaction_id;

ALTER TABLE cc_payment 
RENAME COLUMN cc_payment_uuid TO cc_payment_id;
ALTER TABLE cc_payment 
RENAME COLUMN cc_revolving_line_uuid TO cc_revolving_line_id;
ALTER TABLE cc_payment 
RENAME COLUMN cc_statement_uuid TO cc_statement_id;
ALTER TABLE cc_payment 
RENAME COLUMN transaction_uuid TO transaction_id;

-- ========================================================================
-- STEP 5: Add primary key constraints and foreign key constraints
-- ========================================================================

-- Add primary key constraints
ALTER TABLE cc_revolving_line ADD PRIMARY KEY (cc_revolving_line_id);
ALTER TABLE cc_billing_cycle ADD PRIMARY KEY (cc_billing_cycle_id);
ALTER TABLE cc_statement ADD PRIMARY KEY (cc_statement_id);
ALTER TABLE cc_transaction ADD PRIMARY KEY (cc_transaction_id);
ALTER TABLE cc_payment ADD PRIMARY KEY (cc_payment_id);

-- Add foreign key constraints
ALTER TABLE cc_billing_cycle 
ADD CONSTRAINT fk_cycle_line 
FOREIGN KEY (cc_revolving_line_id) 
REFERENCES cc_revolving_line (cc_revolving_line_id);

ALTER TABLE cc_statement 
ADD CONSTRAINT fk_statement_cycle 
FOREIGN KEY (cc_billing_cycle_id) 
REFERENCES cc_billing_cycle (cc_billing_cycle_id);

ALTER TABLE cc_transaction 
ADD CONSTRAINT fk_txn_line 
FOREIGN KEY (cc_revolving_line_id) 
REFERENCES cc_revolving_line (cc_revolving_line_id);

ALTER TABLE cc_payment 
ADD CONSTRAINT fk_pay_line 
FOREIGN KEY (cc_revolving_line_id) 
REFERENCES cc_revolving_line (cc_revolving_line_id);

-- ========================================================================
-- STEP 6: Add NOT NULL constraints where appropriate
-- ========================================================================

-- Set NOT NULL constraints for primary keys
ALTER TABLE cc_revolving_line ALTER COLUMN cc_revolving_line_id SET NOT NULL;
ALTER TABLE cc_billing_cycle ALTER COLUMN cc_billing_cycle_id SET NOT NULL;
ALTER TABLE cc_statement ALTER COLUMN cc_statement_id SET NOT NULL;
ALTER TABLE cc_transaction ALTER COLUMN cc_transaction_id SET NOT NULL;
ALTER TABLE cc_payment ALTER COLUMN cc_payment_id SET NOT NULL;

-- Set NOT NULL constraints for required foreign keys
ALTER TABLE cc_billing_cycle ALTER COLUMN cc_revolving_line_id SET NOT NULL;
ALTER TABLE cc_statement ALTER COLUMN cc_billing_cycle_id SET NOT NULL;
ALTER TABLE cc_transaction ALTER COLUMN cc_revolving_line_id SET NOT NULL;
ALTER TABLE cc_payment ALTER COLUMN cc_revolving_line_id SET NOT NULL;

-- Note: cc_statement_id in cc_payment and transaction_id fields remain nullable
-- as they represent optional external references
