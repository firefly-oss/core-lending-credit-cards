package com.catalis.core.lending.cards.interfaces.dtos;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.cards.interfaces.enums.CurrencyCodeEnum;
import com.catalis.core.lending.cards.interfaces.enums.TransactionStatusEnum;
import com.catalis.core.lending.cards.interfaces.enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CcTransactionDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ccTransactionId;

    @FilterableId
    private Long ccRevolvingLineId;

    @FilterableId
    private Long transactionId;    // external reference

    private BigDecimal transactionAmount;
    private CurrencyCodeEnum currencyCode;
    private LocalDateTime transactionDate; // note: we store date/time
    private TransactionTypeEnum transactionType;     // PURCHASE, FEE, etc.
    private TransactionStatusEnum transactionStatus; // POSTED, PENDING, etc.
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}