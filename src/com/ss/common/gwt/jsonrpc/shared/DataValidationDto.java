package com.ss.common.gwt.jsonrpc.shared;

import com.google.gson.annotations.SerializedName;

public class DataValidationDto implements JsonDto {

	public static final String PARAM_FIELD = "f";
	public static final String PARAM_MSG = "m";

	@SerializedName(PARAM_FIELD)
	private String field;

	@SerializedName(PARAM_MSG)
	private String msg;

	public DataValidationDto() {

	}

	public DataValidationDto(String field, String msg) {
		this.field = field;
		this.msg = msg;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
