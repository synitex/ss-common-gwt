package com.ss.common.gwt.validate.shared;

import java.util.ArrayList;
import java.util.List;

import com.ss.common.gwt.validate.shared.validators.DurationValidator;
import com.ss.common.gwt.validate.shared.validators.EmailValidator;
import com.ss.common.gwt.validate.shared.validators.EmptyValidator;
import com.ss.common.gwt.validate.shared.validators.MaxLengthValidator;
import com.ss.common.gwt.validate.shared.validators.MinLengthValidator;

/**
 * Validators factory.
 * 
 * @author sergey.sinica
 * 
 */
public class ValidatorFactory {

	private ValidatorFactory() {

	}

	public static List<Validator<String>> getClientNameValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new MaxLengthValidator(100));
		return validators;
	}

	public static List<Validator<String>> getClientEmailValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new MaxLengthValidator(100));
		validators.add(new EmailValidator());
		return validators;
	}

	public static List<Validator<String>> getClientPhoneValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new MaxLengthValidator(20));
		return validators;
	}

	public static List<Validator<String>> getClientCommentValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new MaxLengthValidator(200));
		return validators;
	}

	public static List<Validator<String>> getServiceNameValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new EmptyValidator<String>());
		validators.add(new MinLengthValidator(2));
		validators.add(new MaxLengthValidator(100));
		return validators;
	}

	public static List<Validator<String>> getStaffNameValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new EmptyValidator<String>());
		validators.add(new MinLengthValidator(2));
		validators.add(new MaxLengthValidator(100));
		return validators;
	}

	public static List<Validator<String>> getServiceDurationValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new EmptyValidator<String>());
		validators.add(new DurationValidator());
		return validators;
	}

	public static List<Validator<String>> getPasswordValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new EmptyValidator<String>());
		validators.add(new MinLengthValidator(6));
		validators.add(new MaxLengthValidator(100));
		return validators;
	}

	public static List<Validator<String>> getEmailValidators() {
		ArrayList<Validator<String>> validators = new ArrayList<Validator<String>>();
		validators.add(new EmptyValidator<String>());
		validators.add(new MaxLengthValidator(100));
		validators.add(new EmailValidator());
		return validators;
	}

}
