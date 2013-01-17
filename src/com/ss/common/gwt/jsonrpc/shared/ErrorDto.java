package com.ss.common.gwt.jsonrpc.shared;

import com.google.gson.annotations.SerializedName;

public abstract class ErrorDto implements JsonDto {

	public static final String JSON_MESSAGE = "m";
	public static final String JSON_ERROR_FLAG = "error";
	public static final String ERROR_FLAG = "error";

	@SerializedName(JSON_MESSAGE)
	private String message;

	@SerializedName(JSON_ERROR_FLAG)
	private String errorFlag = ERROR_FLAG;

	public ErrorDto() {

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

	public String getErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}

}
