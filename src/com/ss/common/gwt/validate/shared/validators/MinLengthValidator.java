package com.ss.common.gwt.validate.shared.validators;

import com.ss.common.gwt.validate.shared.Validator;
import com.ss.common.gwt.validate.shared.ValidatorResult;
import com.ss.common.gwt.validate.shared.ValidatorValueProvider;


public class MinLengthValidator implements Validator<String> {

    private int length;

    public MinLengthValidator(int length) {
        this.length = length;
    }

    @Override
	public ValidatorResult validate(ValidatorValueProvider<String> valueProvider) {
		String value = valueProvider.getValue();
        if (value == null) {
            return null;
        }
        int l = value.length();
        if (l < length) {
            return createError();
        }
        return null;
    }

    private ValidatorResult createError() {
		return new ValidatorResult("min.length");
    }

}
