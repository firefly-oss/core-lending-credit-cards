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

import com.firefly.core.lending.cards.interfaces.enums.RevolveStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cc_revolving_line")
public class CcRevolvingLine {

    @Id
    @Column("cc_revolving_line_id")
    private UUID ccRevolvingLineId;

    @Column("account_id")
    private UUID accountId;   // external reference to Accounts domain

    @Column("card_id")
    private UUID cardId;      // external reference to Card Management domain

    @Column("product_id")
    private UUID productId;   // external reference to Product domain (if relevant)

    @Column("credit_limit")
    private BigDecimal creditLimit;

    @Column("interest_rate")
    private BigDecimal interestRate; // e.g. 0.19 for 19% APR

    @Column("current_balance")
    private BigDecimal currentBalance;

    @Column("available_credit")
    private BigDecimal availableCredit;

    @Column("revolve_status")
    private RevolveStatusEnum revolveStatus;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}