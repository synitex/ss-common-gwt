package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.json.client.JSONObject;
import com.ss.common.gwt.jsonrpc.shared.DataValidationDto;
import com.ss.common.gwt.util.client.JSONHelper;

public class GwtDataValidationDto extends DataValidationDto implements GwtJsonDto {

	@Override
	public JSONObject toJsonObject() {
		return new GwtJsonBuilder()
			.append(PARAM_FIELD, getField())
			.append(PARAM_MSG, getMsg())
			.toJson();
	}

	@Override
	public GwtDataValidationDto fromJson(JSONObject json) {
		setField(JSONHelper.getString(json, PARAM_FIELD));
		setMsg(JSONHelper.getString(json, PARAM_MSG));
		return this;
	}

}
