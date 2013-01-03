package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.json.client.JSONObject;
import com.ss.common.gwt.jsonrpc.shared.JsonDTO;

public interface GwtJsonDTO extends JsonDTO {

	JSONObject toJsonObject();

}
