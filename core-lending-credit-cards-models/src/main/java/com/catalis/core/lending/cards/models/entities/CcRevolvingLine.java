package com.catalis.core.lending.cards.models.entities;

import com.catalis.core.lending.cards.interfaces.enums.RevolveStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cc_revolving_line")
public class CcRevolvingLine {

    @Id
    @Column("cc_revolving_line_id")
    private Long ccRevolvingLineId;

    @Column("account_id")
    private Long accountId;   // external reference to Accounts domain

    @Column("card_id")
    private Long cardId;      // external reference to Card Management domain

    @Column("product_id")
    private Long productId;   // external reference to Product domain (if relevant)

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