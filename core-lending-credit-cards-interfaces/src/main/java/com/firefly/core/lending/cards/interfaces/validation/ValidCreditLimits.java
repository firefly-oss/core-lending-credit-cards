package com.firefly.core.lending.cards.interfaces.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom validation annotation to ensure credit limit business rules are followed:
 * - Available credit should not exceed credit limit
 * - Current balance + available credit should equal credit limit (approximately)
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCreditLimitsValidator.class)
@Documented
public @interface ValidCreditLimits {
    
    String message() default "Credit limit, current balance, and available credit values are inconsistent";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
