package com.firefly.core.lending.cards.interfaces.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom validation annotation to ensure that start date is before end date
 * in billing cycles and other date range scenarios.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateRangeValidator.class)
@Documented
public @interface ValidDateRange {
    
    String message() default "Start date must be before end date";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    /**
     * The field name for the start date
     */
    String startDateField() default "cycleStartDate";
    
    /**
     * The field name for the end date
     */
    String endDateField() default "cycleEndDate";
}
