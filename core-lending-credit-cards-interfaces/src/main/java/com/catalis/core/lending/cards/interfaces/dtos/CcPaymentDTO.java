package com.catalis.core.lending.cards.interfaces.dtos;

import com.catalis.core.utils.annotations.FilterableId;
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
public class CcPaymentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ccPaymentId;

    @FilterableId
    private Long ccRevolvingLineId;

    @FilterableId
    private Long ccStatementId;      // optional link to statement

    @FilterableId
    private Long transactionId;      // external payment ref

    private BigDecimal paymentAmount;
    private LocalDateTime paymentDate;
    private Boolean isPartialPayment;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}