package dev.Innocent.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InnoCodeConstraintValidator implements ConstraintValidator<InnoCode, String>{

    private String coursePrefix;
    @Override
    public void initialize(InnoCode innoCode) {
        coursePrefix = innoCode.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        if (s != null){
           result = s.startsWith(coursePrefix);
        }
        else {
            result = true;
        }
        return result;
    }
}
