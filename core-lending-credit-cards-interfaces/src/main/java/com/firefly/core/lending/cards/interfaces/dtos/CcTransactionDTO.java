/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.cards.interfaces.dtos;

import com.firefly.core.lending.cards.interfaces.enums.CurrencyCodeEnum;
import com.firefly.core.lending.cards.interfaces.enums.TransactionStatusEnum;
import com.firefly.core.lending.cards.interfaces.enums.TransactionTypeEnum;
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
public class CcTransactionDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID ccTransactionId;

    @FilterableId
    @NotNull(message = "Revolving line ID is required")
    private UUID ccRevolvingLineId;

    @FilterableId
    private UUID transactionId; // Optional external reference

    @NotNull(message = "Transaction amount is required")
    @DecimalMin(value = "-999999999.99", message = "Transaction amount cannot be less than -999,999,999.99")
    @DecimalMax(value = "999999999.99", message = "Transaction amount cannot exceed 999,999,999.99")
    @Digits(integer = 9, fraction = 2, message = "Transaction amount must have at most 9 integer digits and 2 decimal places")
    private BigDecimal transactionAmount;

    @NotNull(message = "Currency code is required")
    private CurrencyCodeEnum currencyCode;

    @NotNull(message = "Transaction date is required")
    @PastOrPresent(message = "Transaction date cannot be in the future")
    private LocalDateTime transactionDate;

    @NotNull(message = "Transaction type is required")
    private TransactionTypeEnum transactionType;

    @NotNull(message = "Transaction status is required")
    private TransactionStatusEnum transactionStatus;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}