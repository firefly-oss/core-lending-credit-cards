package com.firefly.core.lending.cards.interfaces.dtos;

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
public class CcStatementDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID ccStatementId;

    @FilterableId
    @NotNull(message = "Billing cycle ID is required")
    private UUID ccBillingCycleId;

    @NotNull(message = "Statement date is required")
    @PastOrPresent(message = "Statement date cannot be in the future")
    private LocalDate statementDate;

    @NotNull(message = "Statement balance is required")
    @DecimalMin(value = "-999999999.99", message = "Statement balance cannot be less than -999,999,999.99")
    @DecimalMax(value = "999999999.99", message = "Statement balance cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Statement balance must have at most 9 integer digits and 2 decimal places")
    private BigDecimal statementBalance;

    @NotNull(message = "Minimum payment is required")
    @DecimalMin(value = "0.00", message = "Minimum payment must be non-negative")
    @DecimalMax(value = "999999999.99", message = "Minimum payment cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Minimum payment must have at most 9 integer digits and 2 decimal places")
    private BigDecimal minimumPayment;

    @NotNull(message = "Payment due date is required")
    @Future(message = "Payment due date must be in the future")
    private LocalDate paymentDueDate;

    @NotNull(message = "Payment status (isPaid) is required")
    private Boolean isPaid;

    private LocalDate paidDate; // Optional - only set when isPaid is true

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}