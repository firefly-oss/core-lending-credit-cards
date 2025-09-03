package com.firefly.core.lending.cards.interfaces.validation;

import com.firefly.core.lending.cards.interfaces.dtos.CcRevolvingLineDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

/**
 * Validator implementation for ValidCreditLimits annotation.
 * Validates credit limit business rules for revolving credit lines.
 */
public class ValidCreditLimitsValidator implements ConstraintValidator<ValidCreditLimits, CcRevolvingLineDTO> {
    
    @Override
    public void initialize(ValidCreditLimits constraintAnnotation) {
        // No initialization needed
    }
    
    @Override
    public boolean isValid(CcRevolvingLineDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true; // Let @NotNull handle null validation
        }
        
        BigDecimal creditLimit = dto.getCreditLimit();
        BigDecimal currentBalance = dto.getCurrentBalance();
        BigDecimal availableCredit = dto.getAvailableCredit();
        
        // If any required field is null, let individual field validation handle it
        if (creditLimit == null || currentBalance == null || availableCredit == null) {
            return true;
        }
        
        boolean isValid = true;
        context.disableDefaultConstraintViolation();
        
        // Rule 1: Available credit should not exceed credit limit
        if (availableCredit.compareTo(creditLimit) > 0) {
            context.buildConstraintViolationWithTemplate(
                "Available credit cannot exceed credit limit")
                   .addPropertyNode("availableCredit")
                   .addConstraintViolation();
            isValid = false;
        }
        
        // Rule 2: Available credit should be non-negative
        if (availableCredit.compareTo(BigDecimal.ZERO) < 0) {
            context.buildConstraintViolationWithTemplate(
                "Available credit cannot be negative")
                   .addPropertyNode("availableCredit")
                   .addConstraintViolation();
            isValid = false;
        }
        
        // Rule 3: For positive balances, available credit should be credit limit minus current balance
        // Allow for small rounding differences (0.01)
        if (currentBalance.compareTo(BigDecimal.ZERO) >= 0) {
            BigDecimal expectedAvailableCredit = creditLimit.subtract(currentBalance);
            BigDecimal difference = availableCredit.subtract(expectedAvailableCredit).abs();
            BigDecimal tolerance = new BigDecimal("0.01");
            
            if (difference.compareTo(tolerance) > 0) {
                context.buildConstraintViolationWithTemplate(
                    String.format("Available credit (%s) should approximately equal credit limit (%s) minus current balance (%s) = %s", 
                                availableCredit, creditLimit, currentBalance, expectedAvailableCredit))
                       .addPropertyNode("availableCredit")
                       .addConstraintViolation();
                isValid = false;
            }
        }
        
        return isValid;
    }
}
