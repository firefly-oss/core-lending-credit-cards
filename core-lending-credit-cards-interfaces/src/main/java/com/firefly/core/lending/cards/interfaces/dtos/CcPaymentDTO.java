package com.firefly.core.lending.cards.interfaces.dtos;

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
public class CcPaymentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID ccPaymentId;

    @FilterableId
    @NotNull(message = "Revolving line ID is required")
    private UUID ccRevolvingLineId;

    @FilterableId
    private UUID ccStatementId; // Optional link to statement

    @FilterableId
    private UUID transactionId; // Optional external payment reference

    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.01", message = "Payment amount must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Payment amount cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Payment amount must have at most 9 integer digits and 2 decimal places")
    private BigDecimal paymentAmount;

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDateTime paymentDate;

    @NotNull(message = "Partial payment flag is required")
    private Boolean isPartialPayment;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}