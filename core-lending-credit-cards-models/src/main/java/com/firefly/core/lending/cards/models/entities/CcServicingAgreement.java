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


package com.firefly.core.lending.cards.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cc_servicing_agreement")
public class CcServicingAgreement {

    @Id
    @Column("cc_servicing_agreement_id")
    private UUID ccServicingAgreementId;

    @Column("cc_revolving_line_id")
    private UUID ccRevolvingLineId; // references CcRevolvingLine

    @Column("loan_servicing_case_id")
    private UUID loanServicingCaseId; // external reference to LoanServicingCase in core-lending-loan-servicing

    @Column("agreement_number")
    private String agreementNumber; // unique agreement identifier

    @Column("agreement_date")
    private LocalDate agreementDate;

    @Column("effective_date")
    private LocalDate effectiveDate;

    @Column("termination_date")
    private LocalDate terminationDate;

    @Column("is_active")
    private Boolean isActive;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

