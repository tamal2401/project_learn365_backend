package org.learn365.modal.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MobileValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileNumberValidation {

	boolean required() default false;

	String message() default "Invalid Mobile number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
