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
