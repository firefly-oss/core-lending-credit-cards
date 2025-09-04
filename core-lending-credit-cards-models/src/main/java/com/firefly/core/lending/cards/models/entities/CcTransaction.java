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

import com.firefly.core.lending.cards.interfaces.enums.CurrencyCodeEnum;
import com.firefly.core.lending.cards.interfaces.enums.TransactionStatusEnum;
import com.firefly.core.lending.cards.interfaces.enums.TransactionTypeEnum;
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
@Table("cc_transaction")
public class CcTransaction {

    @Id
    @Column("cc_transaction_id")
    private UUID ccTransactionId;

    @Column("cc_revolving_line_id")
    private UUID ccRevolvingLineId; // references CcRevolvingLine

    @Column("transaction_id")
    private UUID transactionId;      // external ref (Transactions domain)

    @Column("transaction_amount")
    private BigDecimal transactionAmount;

    @Column("currency_code")
    private CurrencyCodeEnum currencyCode;

    @Column("transaction_date")
    private LocalDateTime transactionDate;

    @Column("transaction_type")
    private TransactionTypeEnum transactionType;

    @Column("transaction_status")
    private TransactionStatusEnum transactionStatus;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}