package com.catalis.core.lending.cards.models.entities;

import com.catalis.core.lending.cards.interfaces.enums.CurrencyCodeEnum;
import com.catalis.core.lending.cards.interfaces.enums.TransactionStatusEnum;
import com.catalis.core.lending.cards.interfaces.enums.TransactionTypeEnum;
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
@Table("cc_transaction")
public class CcTransaction {

    @Id
    @Column("cc_transaction_id")
    private Long ccTransactionId;

    @Column("cc_revolving_line_id")
    private Long ccRevolvingLineId; // references CcRevolvingLine

    @Column("transaction_id")
    private Long transactionId;      // external ref (Transactions domain)

    @Column("transaction_amount")
    private BigDecimal transactionAmount;

    @Column("currency_code")
    private CurrencyCodeEnum currencyCode;

    @Column("transaction_date")
    private LocalDateTime transactionDate;

    @Column("transaction_type")
    private TransactionTypeEnum transactionType;

    @Column("transaction_status")
    private TransactionStatusEnum transactionStatus;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}