package org.learn365.modal.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<MobileNumberValidation, String> {

	private boolean required;

	public void initialize(MobileNumberValidation constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return true;
	}

}
