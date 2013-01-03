package com.ss.common.gwt.jsonrpc.client;

import java.util.Collection;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.ss.common.gwt.util.client.UIHelper;

public class GwtJsonDTOBuilder {

	private JSONObject json;

	public GwtJsonDTOBuilder() {
		json = new JSONObject();
	}

	public <T extends GwtJsonDTO> GwtJsonDTOBuilder append(String param, T dto) {
		if (dto == null) {
			return this;
		}
		JSONObject jsonObj = dto.toJsonObject();
		json.put(param, jsonObj);
		return this;
	}

	public <T extends GwtJsonDTO> GwtJsonDTOBuilder append(String param, Collection<T> dtos) {
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
