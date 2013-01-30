package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.ss.common.gwt.jsonrpc.shared.DataValidationDto;
import com.ss.common.gwt.jsonrpc.shared.ErrorDto;
import com.ss.common.gwt.util.client.JSONHelper;
import com.ss.common.gwt.util.client.JSONHelper.ValueParser;

public class GwtErrorDto extends ErrorDto implements GwtJsonDto {

	@Override
	public JSONObject toJsonObject() {
		return new GwtJsonBuilder()
			.append(PARAM_MESSAGE, getMessage())
			.append(PARAM_KEY, getKey())
			.append(PARAM_PREFIX, getPrefix())
			.append(PARAM_ID, getId())
			.append(PARAM_VALIDATIONS, getValidations())
			.toJson();		
	}

	@Override
	public GwtErrorDto fromJson(JSONObject data) {
		setMessage(JSONHelper.getString(data, PARAM_MESSAGE));
		setKey(JSONHelper.getString(data, PARAM_KEY));
		setPrefix(JSONHelper.getString(data, PARAM_PREFIX));
		setId(JSONHelper.getString(data, PARAM_ID));
		setValidations(JSONHelper.getArray(data, PARAM_VALIDATIONS, new ValueParser<DataValidationDto>() {
			@Override
			public GwtDataValidationDto parse(JSONValue json) {
				if(json == null || json.isObject() == null) {
					return null;
				} else {
					return new GwtDataValidationDto().fromJson(json.isObject());
				}
			}
		}));
		return this;
	}

}
