-- V2 - CREATE TABLES FOR THE CREDIT CARD REVOLVING LENDING SUBMODULE

-- ========================================================================
-- TABLE: cc_revolving_line
-- ========================================================================
CREATE TABLE IF NOT EXISTS cc_revolving_line (
                                                 cc_revolving_line_id   BIGSERIAL PRIMARY KEY,
                                                 account_id             BIGINT,       -- external reference to Accounts domain
                                                 card_id                BIGINT,       -- external reference to Card Management domain
                                                 product_id             BIGINT,       -- external reference to Product domain
                                                 credit_limit           DECIMAL(18,2),
    interest_rate          DECIMAL(9,4), -- interest rate for revolve balance
    current_balance        DECIMAL(18,2) DEFAULT 0,
    available_credit       DECIMAL(18,2) DEFAULT 0,
    revolve_status         revolve_status NOT NULL, -- e.g. ACTIVE, BLOCKED
    remarks                TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: cc_billing_cycle
-- ========================================================================
CREATE TABLE IF NOT EXISTS cc_billing_cycle (
                                                cc_billing_cycle_id    BIGSERIAL PRIMARY KEY,
                                                cc_revolving_line_id   BIGINT NOT NULL,
                                                cycle_start_date       DATE,
                                                cycle_end_date         DATE,
                                                cycle_open_balance     DECIMAL(18,2) DEFAULT 0, -- revolve balance at start
    cycle_close_balance    DECIMAL(18,2) DEFAULT 0, -- revolve balance at end
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_cycle_line
    FOREIGN KEY (cc_revolving_line_id)
    REFERENCES cc_revolving_line (cc_revolving_line_id)
    );

-- ========================================================================
-- TABLE: cc_statement
-- ========================================================================
CREATE TABLE IF NOT EXISTS cc_statement (
                                            cc_statement_id        BIGSERIAL PRIMARY KEY,
                                            cc_billing_cycle_id    BIGINT NOT NULL,
                                            statement_date         DATE NOT NULL,
                                            statement_balance      DECIMAL(18,2) NOT NULL, -- total due on statement
    minimum_payment        DECIMAL(18,2) NOT NULL,
    payment_due_date       DATE NOT NULL,
    is_paid                BOOLEAN DEFAULT FALSE,
    paid_date              DATE,
    note                   TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_statement_cycle
    FOREIGN KEY (cc_billing_cycle_id)
    REFERENCES cc_billing_cycle (cc_billing_cycle_id)
    );

-- ========================================================================
-- TABLE: cc_transaction
-- ========================================================================
CREATE TABLE IF NOT EXISTS cc_transaction (
                                              cc_transaction_id      BIGSERIAL PRIMARY KEY,
                                              cc_revolving_line_id   BIGINT NOT NULL,
                                              transaction_id         BIGINT,      -- external reference (Transactions domain)
                                              transaction_amount     DECIMAL(18,2) NOT NULL,
    currency_code          currency_code NOT NULL,
    transaction_date       TIMESTAMP NOT NULL,
    transaction_type       transaction_type NOT NULL,
    transaction_status     transaction_status NOT NULL,
    note                   TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_txn_line
    FOREIGN KEY (cc_revolving_line_id)
    REFERENCES cc_revolving_line (cc_revolving_line_id)
    );

-- ========================================================================
-- TABLE: cc_payment
-- ========================================================================
CREATE TABLE IF NOT EXISTS cc_payment (
                                          cc_payment_id          BIGSERIAL PRIMARY KEY,
                                          cc_revolving_line_id   BIGINT NOT NULL,
                                          cc_statement_id        BIGINT,      -- optional link if paying a specific statement
                                          transaction_id         BIGINT,      -- external reference to Payment domain
                                          payment_amount         DECIMAL(18,2) NOT NULL,
    payment_date           TIMESTAMP NOT NULL,
    is_partial_payment     BOOLEAN DEFAULT FALSE,
    note                   TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_pay_line
    FOREIGN KEY (cc_revolving_line_id)
    REFERENCES cc_revolving_line (cc_revolving_line_id)
    );
