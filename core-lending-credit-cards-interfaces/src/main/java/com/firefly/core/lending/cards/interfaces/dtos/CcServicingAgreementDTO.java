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

import com.firefly.core.lending.cards.interfaces.validation.ValidDateRange;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidDateRange(startDateField = "effectiveDate", endDateField = "terminationDate",
               message = "Effective date must be before or equal to termination date")
public class CcServicingAgreementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID ccServicingAgreementId;

    @FilterableId
    @NotNull(message = "Revolving line ID is required")
    private UUID ccRevolvingLineId;

    @FilterableId
    @NotNull(message = "Loan servicing case ID is required")
    private UUID loanServicingCaseId;

    @NotBlank(message = "Agreement number is required")
    @Size(max = 100, message = "Agreement number cannot exceed 100 characters")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Agreement number must contain only uppercase letters, numbers, and hyphens")
    private String agreementNumber;

    @NotNull(message = "Agreement date is required")
    @PastOrPresent(message = "Agreement date cannot be in the future")
    private LocalDate agreementDate;

    @NotNull(message = "Effective date is required")
    private LocalDate effectiveDate;

    @Future(message = "Termination date must be in the future")
    private LocalDate terminationDate;

    @NotNull(message = "Active status is required")
    private Boolean isActive;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}

