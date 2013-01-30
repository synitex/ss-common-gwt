package com.ss.common.gwt.validate.shared.validators;

import com.ss.common.gwt.validate.shared.Validator;
import com.ss.common.gwt.validate.shared.ValidatorResult;
import com.ss.common.gwt.validate.shared.ValidatorValueProvider;


public class LatinValidator implements Validator<String> {

    @Override
	public ValidatorResult validate(ValidatorValueProvider<String> valueProvider) {
		String value = valueProvider.getValue();
        if (value == null) {
            return null;
        }
        String latin = "\\w+";
        if (value.matches(latin)) {
            return null;
        } else {
			return new ValidatorResult("not.latin");
        }
    }

}
