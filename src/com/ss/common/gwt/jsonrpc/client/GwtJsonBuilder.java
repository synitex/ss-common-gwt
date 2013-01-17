package com.ss.common.gwt.jsonrpc.client;

import java.util.Collection;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.ss.common.gwt.util.client.UIHelper;

public class GwtJsonBuilder {

	private JSONObject json;

	public GwtJsonBuilder() {
		json = new JSONObject();
	}

	public GwtJsonBuilder append(String param, String value) {
		if (UIHelper.isEmpty(value)) {
			return this;
		}
		json.put(param, new JSONString(value));
		return this;
	}

	public GwtJsonBuilder append(String param, List<String> values) {
		if (UIHelper.isEmpty(values)) {
			return this;
		}
		JSONArray array = new JSONArray();
		int index = 0;
		for (String value : values) {
			array.set(index, new JSONString(value));
			index++;
		}
		json.put(param, array);
		return this;
	}

	public <T extends GwtJsonDto> GwtJsonBuilder append(String param, T dto) {
		if (dto == null) {
			return this;
		}
		JSONObject jsonObj = dto.toJsonObject();
		json.put(param, jsonObj);
		return this;
	}

	public <T extends GwtJsonDto> GwtJsonBuilder append(String param, Collection<T> dtos) {
		if (UIHelper.isEmpty(dtos)) {
			return this;
		}
		JSONArray array = new JSONArray();
		int index = 0;
		for (T dto : dtos) {
			JSONObject jsonObj = dto.toJsonObject();
			array.set(index, jsonObj);
			index++;
		}
		json.put(param, array);
		return this;
	}

	public String toString() {
		return json.toString();
	}

	public JSONObject toJson() {
		return json;
	}

}
