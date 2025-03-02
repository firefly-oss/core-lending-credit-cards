package com.catalis.core.lending.cards.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cc_payment")
public class CcPayment {

    @Id
    @Column("cc_payment_id")
    private Long ccPaymentId;

    @Column("cc_revolving_line_id")
    private Long ccRevolvingLineId; // references CcRevolvingLine

    @Column("cc_statement_id")
    private Long ccStatementId;     // optional link to a CcStatement

    @Column("transaction_id")
    private Long transactionId;     // external ref to Payment domain

    @Column("payment_amount")
    private BigDecimal paymentAmount;

    @Column("payment_date")
    private LocalDateTime paymentDate;

    @Column("is_partial_payment")
    private Boolean isPartialPayment;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}