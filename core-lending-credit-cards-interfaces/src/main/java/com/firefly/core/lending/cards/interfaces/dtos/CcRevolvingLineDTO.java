package com.firefly.core.lending.cards.interfaces.dtos;

import com.firefly.core.lending.cards.interfaces.enums.RevolveStatusEnum;
import com.firefly.core.lending.cards.interfaces.validation.ValidCreditLimits;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidCreditLimits
public class CcRevolvingLineDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID ccRevolvingLineId;

    @FilterableId
    @NotNull(message = "Account ID is required")
    private UUID accountId;

    @FilterableId
    @NotNull(message = "Card ID is required")
    private UUID cardId;

    @FilterableId
    private UUID productId; // Optional external reference

    @NotNull(message = "Credit limit is required")
    @DecimalMin(value = "0.00", message = "Credit limit must be non-negative")
    @DecimalMax(value = "999999999.99", message = "Credit limit cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Credit limit must have at most 9 integer digits and 2 decimal places")
    private BigDecimal creditLimit;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.0000", message = "Interest rate must be non-negative")
    @DecimalMax(value = "1.0000", message = "Interest rate cannot exceed 100% (1.0000)")
    @Digits(integer = 1, fraction = 4, message = "Interest rate must have at most 1 integer digit and 4 decimal places")
    private BigDecimal interestRate;

    @NotNull(message = "Current balance is required")
    @DecimalMin(value = "-999999999.99", message = "Current balance cannot be less than -999,999,999.99")
    @DecimalMax(value = "999999999.99", message = "Current balance cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Current balance must have at most 9 integer digits and 2 decimal places")
    private BigDecimal currentBalance;

    @NotNull(message = "Available credit is required")
    @DecimalMin(value = "0.00", message = "Available credit must be non-negative")
    @DecimalMax(value = "999999999.99", message = "Available credit cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Available credit must have at most 9 integer digits and 2 decimal places")
    private BigDecimal availableCredit;

    @NotNull(message = "Revolve status is required")
    private RevolveStatusEnum revolveStatus;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}