package com.ss.common.gwt.validate.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.ss.common.gwt.util.client.UIHelper;
import com.ss.common.gwt.validate.shared.Validator;
import com.ss.common.gwt.validate.shared.ValidatorResult;
import com.ss.common.gwt.validate.shared.ValidatorValueProvider;

/**
 * Field validator instance.
 * 
 * @see GwtValidatorForm
 * @author sergey.sinica
 * 
 * @param <T>
 */
public class GwtValidatorField<T> {

    private Element el;
    private List<Validator<T>> validators = new ArrayList<Validator<T>>();
    private ValidatorValueProvider<T> valueProvider;
	private GwtValidatorMessagesProvider messagesProvider;

	GwtValidatorField(Element el, ValidatorValueProvider<T> valueProvider) {
        this.el = el;
		this.valueProvider = valueProvider;
    }

	GwtValidatorField(com.google.gwt.user.client.Element el, ValidatorValueProvider<T> valueProvider) {
        this.el = el.cast();
		this.valueProvider = valueProvider;
    }

	public GwtValidatorField<T> addValidator(Validator<T> validator) {
        validators.add(validator);
        return this;
    }

	public GwtValidatorField<T> addValidators(List<Validator<T>> validators) {
		this.validators.addAll(validators);
		return this;
	}

    public boolean isValid() {
        List<ValidatorResult> errors = new ArrayList<ValidatorResult>();
        for (Validator<T> v : validators) {
			ValidatorResult r = v.validate(valueProvider);
            if (r != null) {
                errors.add(r);
            }
        }
        if (errors.size() > 0) {
            markErrors(errors);
        }
        return errors.size() == 0;
    }

    public void clearErrors() {
        Element next = el.getNextSiblingElement();
        if (next == null) {
            return;
        }
        String className = next.getClassName();
        if(className == null) {
            return;
        }
        if(className.contains("errors-container")) {
            next.removeFromParent();
        }
    }

    private void markErrors(List<ValidatorResult> errors) {
        com.google.gwt.user.client.Element container = DOM.createDiv();
        container.setClassName("errors-container");
        for (ValidatorResult r : errors) {
            com.google.gwt.user.client.Element row = DOM.createSpan();
            row.setClassName("error");
			String msg = messagesProvider.getValidationMessage(r.getErrorKey());
            row.setInnerHTML(UIHelper.safeString(msg));
            container.appendChild(row);
        }
        Element parent = el.getParentElement();
        parent.insertAfter(container, el);
    }

	public void setMessagesProvider(GwtValidatorMessagesProvider messagesProvider) {
		this.messagesProvider = messagesProvider;
	}

}
