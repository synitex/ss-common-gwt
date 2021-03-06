package com.ss.common.gwt.validate.shared.validators;

import com.ss.common.gwt.validate.shared.Validator;
import com.ss.common.gwt.validate.shared.ValidatorResult;
import com.ss.common.gwt.validate.shared.ValidatorValueProvider;


public class EmptyValidator<T> implements Validator<T> {

    @Override
	public ValidatorResult validate(ValidatorValueProvider<T> valueProvider) {
		T value = valueProvider.getValue();
        if (value == null) {
            return createError();
        }
        if (value instanceof String) {
            String casted = (String) value;
            if ("".equals(casted.trim())) {
                return createError();
            }
        }
        return null;
    }

    private ValidatorResult createError() {
		return new ValidatorResult("empty.prohibited");
    }

}
