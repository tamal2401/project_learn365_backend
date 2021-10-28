package org.learn365.modal.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthValidation, LocalDate> {

	private boolean required;

	public void initialize(DateOfBirthValidation constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
		boolean isDateOfBirthValid = true;

		if (required) {
			if (dateOfBirth.getYear() == LocalDate.now().getYear()) {
				isDateOfBirthValid = false;
			}
		}
		return isDateOfBirthValid;
	}
}
