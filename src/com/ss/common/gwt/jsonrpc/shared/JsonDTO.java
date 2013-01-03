package com.ss.common.gwt.jsonrpc.shared;

public interface JsonDTO {

	String toJson();

	<T extends JsonDTO> T fromJson(String data);
	
}
