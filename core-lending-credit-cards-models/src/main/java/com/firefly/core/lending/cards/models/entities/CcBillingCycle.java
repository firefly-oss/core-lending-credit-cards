package com.firefly.core.lending.cards.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("cc_billing_cycle")
public class CcBillingCycle {

    @Id
    @Column("cc_billing_cycle_id")
    private Long ccBillingCycleId;

    @Column("cc_revolving_line_id")
    private Long ccRevolvingLineId; // references CcRevolvingLine

    @Column("cycle_start_date")
    private LocalDate cycleStartDate;

    @Column("cycle_end_date")
    private LocalDate cycleEndDate;

    @Column("cycle_open_balance")
    private BigDecimal cycleOpenBalance;

    @Column("cycle_close_balance")
    private BigDecimal cycleCloseBalance;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}