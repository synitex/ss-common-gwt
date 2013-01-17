package com.ss.common.gwt.jsonrpc.shared;

public interface JsonDto {

	String toJson();

	JsonDto fromJson(String data);
	
}
