package com.ss.common.gwt.jsonrpc.shared;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ErrorDto implements JsonDto {

	public static final String PARAM_ID = "i";
	public static final String PARAM_MESSAGE = "m";
	public static final String PARAM_KEY = "k";
	public static final String PARAM_PREFIX = "p";
	public static final String PARAM_VALIDATIONS = "v";

	@SerializedName(PARAM_ID)
	private String id;

	@SerializedName(PARAM_MESSAGE)
	private String message;

	@SerializedName(PARAM_KEY)
	private String key;

	@SerializedName(PARAM_PREFIX)
	private String prefix;

	@SerializedName(PARAM_VALIDATIONS)
	private List<DataValidationDto> validations;

	public ErrorDto() {

	}

	public ErrorDto(String id) {
		this.id = id;
	}

	public ErrorDto(String id, List<DataValidationDto> validations) {
		this.id = id;
		this.validations = validations;
	}

	public ErrorDto(String id, String key, String prefix, String msg) {
		this.message = msg;
		this.key = key;
		this.prefix = prefix;
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

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public List<DataValidationDto> getValidations() {
		return validations;
	}

	public void setValidations(List<DataValidationDto> validations) {
		this.validations = validations;
	}

}
