package com.javatechie.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidator.class)
public @interface CourseTypeValidation {

    //error message
    public String message() default "Invalid courseType: must be LIVE or RECORDING";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
