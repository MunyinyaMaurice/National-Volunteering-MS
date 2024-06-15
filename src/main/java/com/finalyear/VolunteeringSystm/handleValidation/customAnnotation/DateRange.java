package com.finalyear.VolunteeringSystm.handleValidation.customAnnotation;



        import jakarta.validation.Constraint;
        import jakarta.validation.Payload;
        import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
    String message() default "Start date must be before finish date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
