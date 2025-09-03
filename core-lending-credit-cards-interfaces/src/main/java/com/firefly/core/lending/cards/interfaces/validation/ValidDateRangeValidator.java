package com.firefly.core.lending.cards.interfaces.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * Validator implementation for ValidDateRange annotation.
 * Validates that start date is before end date using reflection to access fields.
 */
public class ValidDateRangeValidator implements ConstraintValidator<ValidDateRange, Object> {
    
    private String startDateField;
    private String endDateField;
    
    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        this.startDateField = constraintAnnotation.startDateField();
        this.endDateField = constraintAnnotation.endDateField();
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null validation
        }
        
        try {
            LocalDate startDate = getFieldValue(value, startDateField);
            LocalDate endDate = getFieldValue(value, endDateField);
            
            // If either date is null, let individual field validation handle it
            if (startDate == null || endDate == null) {
                return true;
            }
            
            boolean isValid = startDate.isBefore(endDate) || startDate.isEqual(endDate);
            
            if (!isValid) {
                // Customize the error message
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    String.format("Start date (%s) must be before or equal to end date (%s)", 
                                startDate, endDate))
                       .addConstraintViolation();
            }
            
            return isValid;
            
        } catch (Exception e) {
            // If reflection fails, assume valid and let other validations handle it
            return true;
        }
    }
    
    private LocalDate getFieldValue(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (LocalDate) field.get(object);
    }
}
