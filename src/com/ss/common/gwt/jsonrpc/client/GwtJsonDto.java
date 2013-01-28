package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.json.client.JSONObject;
import com.ss.common.gwt.jsonrpc.shared.JsonDto;

public interface GwtJsonDto extends JsonDto {

	JSONObject toJsonObject();

	JsonDto fromJson(JSONObject json);

}
