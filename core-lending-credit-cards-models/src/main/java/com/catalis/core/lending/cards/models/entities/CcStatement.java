package com.catalis.core.lending.cards.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cc_statement")
public class CcStatement {

    @Id
    @Column("cc_statement_id")
    private Long ccStatementId;

    @Column("cc_billing_cycle_id")
    private Long ccBillingCycleId; // references CcBillingCycle

    @Column("statement_date")
    private LocalDate statementDate;

    @Column("statement_balance")
    private BigDecimal statementBalance;

    @Column("minimum_payment")
    private BigDecimal minimumPayment;

    @Column("payment_due_date")
    private LocalDate paymentDueDate;

    @Column("is_paid")
    private Boolean isPaid;

    @Column("paid_date")
    private LocalDate paidDate;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}