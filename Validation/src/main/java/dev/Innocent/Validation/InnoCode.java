package dev.Innocent.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = InnoCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InnoCode {

    // Define default  Course Code
    public String value() default "UDO";

    // Define default error Messages
    public String message() default "Must start with UDO";

    // Define default group
    public Class<?>[] groups() default {};

    // Define default Payloads
    public Class<? extends Payload>[] payload() default {};
}
