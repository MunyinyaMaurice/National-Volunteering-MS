package com.finalyear.VolunteeringSystm.handleValidation.customAnnotation;

import com.finalyear.VolunteeringSystm.dto.OpenPositionDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<DateRange, OpenPositionDto> {
    @Override
    public boolean isValid(OpenPositionDto openPositionDto, ConstraintValidatorContext context) {
        if (openPositionDto.getStartDate() == null || openPositionDto.getFinishDate() == null) {
            return true; // Handled by @NotNull
        }
        return openPositionDto.getStartDate().isBefore(openPositionDto.getFinishDate());
    }
}
