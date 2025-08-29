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
public class CcBillingCycleDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ccBillingCycleId;

    @FilterableId
    private Long ccRevolvingLineId;

    private LocalDate cycleStartDate;
    private LocalDate cycleEndDate;
    private BigDecimal cycleOpenBalance;   // revolve balance at start
    private BigDecimal cycleCloseBalance;  // revolve balance at end
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}