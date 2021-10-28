package org.learn365.modal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOfBirthValidation {

	boolean required() default false;

	String message() default "Date Of Birth should not be current year";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
