package com.ss.common.gwt.jsonrpc.client;

import java.util.Collection;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.ss.common.gwt.util.client.UIHelper;

public class GwtJsonBuilder {

	private JSONObject json;

	public GwtJsonBuilder() {
		json = new JSONObject();
	}

	public GwtJsonBuilder append(String param, Object value) {
		if (value == null) {
			return this;
		}
		JSONValue jsonVal = toJsonValue(value);
		if(jsonVal == null) {
			GWT.log("Failed to inject " + value.getClass() + " with param " + param + ", because object can not be transformed to json object.");
			return this;
		}
		json.put(param, jsonVal);
		return this;
	}


	private JSONValue toJsonValue(Object value) {
		if (value instanceof String) {
			return new JSONString((String) value);
		} else if (value instanceof Long) {
			return new JSONString(((Long) value).toString());
		} else if (value instanceof Boolean) {
			return JSONBoolean.getInstance(Boolean.TRUE.equals((Boolean)value));
		} else if (value instanceof Integer) {
			return new JSONString(((Integer) value).toString());
		} else if(value instanceof GwtJsonDto) {
			GwtJsonDto gwtDto = (GwtJsonDto) value;
			return gwtDto.toJsonObject();
		} else if (value instanceof Collection) {
			return listToJsonValue((Collection) value);
		} else {
			return null;
		}
	}

	private JSONValue listToJsonValue(Collection values) {
		if (UIHelper.isEmpty(values)) {
			return null;
		}
		JSONArray array = new JSONArray();
		int index = 0;
		for (Object value : values) {
			JSONValue jsonVal = toJsonValue(value);
			if (jsonVal != null) {
				array.set(index, jsonVal);
			} else {
				GWT.log("Unsupported list type detected " + value.getClass());
			}
			index++;
		}
		return array;
	}

	public String toString() {
		return json.toString();
	}

	public JSONObject toJson() {
		return json;
	}

}
