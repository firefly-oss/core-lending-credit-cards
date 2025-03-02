package com.catalis.core.lending.cards.interfaces.dtos;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.cards.interfaces.enums.RevolveStatusEnum;
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
public class CcRevolvingLineDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ccRevolvingLineId;

    @FilterableId
    private Long accountId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long productId;

    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private BigDecimal currentBalance;
    private BigDecimal availableCredit;
    private RevolveStatusEnum revolveStatus; // ACTIVE, BLOCKED, etc.
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}