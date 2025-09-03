package com.firefly.core.lending.cards.interfaces.dtos;

import com.firefly.core.lending.cards.interfaces.validation.ValidDateRange;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidDateRange(startDateField = "cycleStartDate", endDateField = "cycleEndDate",
               message = "Cycle start date must be before or equal to cycle end date")
public class CcBillingCycleDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID ccBillingCycleId;

    @FilterableId
    @NotNull(message = "Revolving line ID is required")
    private UUID ccRevolvingLineId;

    @NotNull(message = "Cycle start date is required")
    @PastOrPresent(message = "Cycle start date cannot be in the future")
    private LocalDate cycleStartDate;

    @NotNull(message = "Cycle end date is required")
    private LocalDate cycleEndDate;

    @NotNull(message = "Cycle open balance is required")
    @DecimalMin(value = "-999999999.99", message = "Cycle open balance cannot be less than -999,999,999.99")
    @DecimalMax(value = "999999999.99", message = "Cycle open balance cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Cycle open balance must have at most 9 integer digits and 2 decimal places")
    private BigDecimal cycleOpenBalance;

    @NotNull(message = "Cycle close balance is required")
    @DecimalMin(value = "-999999999.99", message = "Cycle close balance cannot be less than -999,999,999.99")
    @DecimalMax(value = "999999999.99", message = "Cycle close balance cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Cycle close balance must have at most 9 integer digits and 2 decimal places")
    private BigDecimal cycleCloseBalance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}