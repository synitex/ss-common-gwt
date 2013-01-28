package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.json.client.JSONObject;
import com.ss.common.gwt.jsonrpc.shared.ErrorDto;
import com.ss.common.gwt.util.client.JSONHelper;
import com.ss.common.gwt.util.client.UIHelper;

public class GwtErrorDto extends ErrorDto implements GwtJsonDto {

	@Override
	public JSONObject toJsonObject() {
		return new GwtJsonBuilder()
			.append(PARAM_MESSAGE, getMessage())
			.append(PARAM_KEY, getKey())
			.append(PARAM_PREFIX, getPrefix())
			.toJson();		
	}

	@Override
	public GwtErrorDto fromJson(JSONObject data) {
		setMessage(JSONHelper.getString(data, PARAM_MESSAGE));
		setKey(JSONHelper.getString(data, PARAM_KEY));
		setPrefix(JSONHelper.getString(data, PARAM_PREFIX));
		return this;
	}

	public boolean isNotEmpty() {
		return !UIHelper.isEmpty(getKey()) || !UIHelper.isEmpty(getMessage());
	}

}
