package com.firefly.core.lending.cards.interfaces.dtos;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CcStatementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ccStatementId;

    @FilterableId
    private Long ccBillingCycleId;

    private LocalDate statementDate;
    private BigDecimal statementBalance;
    private BigDecimal minimumPayment;
    private LocalDate paymentDueDate;
    private Boolean isPaid;
    private LocalDate paidDate;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}