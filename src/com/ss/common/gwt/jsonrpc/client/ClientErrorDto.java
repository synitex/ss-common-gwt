package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.ss.common.gwt.jsonrpc.shared.ErrorDto;
import com.ss.common.gwt.util.client.JSONHelper;
import com.ss.common.gwt.util.client.UIHelper;

public class ClientErrorDto extends ErrorDto implements GwtJsonDto {

	@Override
	public String toJson() {
		return toJsonObject().toString();
	}

	@Override
	public ClientErrorDto fromJson(String data) {
		if (UIHelper.isEmpty(data)) {
			return null;
		}
		JSONObject json = JSONHelper.getJsonFromString(data);
		return fromJson(json);
	}

	@Override
	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		json.put(JSON_MESSAGE, new JSONString(getMessage()));
		json.put(JSON_ERROR_FLAG, new JSONString(ERROR_FLAG));
		return json;
	}

	@Override
	public ClientErrorDto fromJson(JSONObject data) {
		setMessage(JSONHelper.getString(data, JSON_MESSAGE));
		setErrorFlag(JSONHelper.getString(data, JSON_ERROR_FLAG));
		return this;
	}

}
