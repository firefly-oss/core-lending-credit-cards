-- V5 - CREATE SERVICING AGREEMENT TABLE
-- This migration creates the cc_servicing_agreement table to link credit card
-- revolving lines with loan servicing cases from the core-lending-loan-servicing microservice

-- ========================================================================
-- TABLE: cc_servicing_agreement
-- ========================================================================
CREATE TABLE IF NOT EXISTS cc_servicing_agreement (
    cc_servicing_agreement_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    cc_revolving_line_id        UUID NOT NULL,
    loan_servicing_case_id      UUID NOT NULL,  -- external reference to core-lending-loan-servicing
    agreement_number            VARCHAR(100) UNIQUE NOT NULL,
    agreement_date              DATE NOT NULL,
    effective_date              DATE NOT NULL,
    termination_date            DATE,
    is_active                   BOOLEAN NOT NULL DEFAULT TRUE,
    remarks                     TEXT,
    created_at                  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                  TIMESTAMP NOT NULL DEFAULT NOW(),
    
    CONSTRAINT fk_servicing_agreement_line
        FOREIGN KEY (cc_revolving_line_id)
        REFERENCES cc_revolving_line (cc_revolving_line_id)
);

-- Create index on cc_revolving_line_id for faster lookups
CREATE INDEX idx_cc_servicing_agreement_revolving_line 
    ON cc_servicing_agreement (cc_revolving_line_id);

-- Create index on loan_servicing_case_id for faster lookups
CREATE INDEX idx_cc_servicing_agreement_servicing_case 
    ON cc_servicing_agreement (loan_servicing_case_id);

-- Create index on agreement_number for faster lookups
CREATE INDEX idx_cc_servicing_agreement_number 
    ON cc_servicing_agreement (agreement_number);

-- Create index on is_active for filtering active agreements
CREATE INDEX idx_cc_servicing_agreement_active 
    ON cc_servicing_agreement (is_active);

