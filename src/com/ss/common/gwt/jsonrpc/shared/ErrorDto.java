package com.ss.common.gwt.jsonrpc.shared;

import com.google.gson.annotations.SerializedName;

public class ErrorDto implements JsonDto {

	public static final String PARAM_MESSAGE = "m";
	public static final String PARAM_KEY = "k";
	public static final String PARAM_PREFIX = "p";

	@SerializedName(PARAM_MESSAGE)
	private String message;

	@SerializedName(PARAM_KEY)
	private String key;

	@SerializedName(PARAM_PREFIX)
	private String prefix;


	public ErrorDto() {

	}

	public ErrorDto(String msg, String key, String prefix) {
		this.message = msg;
		this.key = key;
		this.prefix = prefix;
	}

	public ErrorDto(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
