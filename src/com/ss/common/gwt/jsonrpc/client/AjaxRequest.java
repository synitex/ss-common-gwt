package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.ss.common.gwt.jsonrpc.shared.JsonRpcConstants;
import com.ss.common.gwt.util.client.JSONHelper;
import com.ss.common.gwt.util.client.UIHelper;

public class AjaxRequest implements RequestCallback {

	private AjaxRequestCallback callback;
	private JSONObject data;
	private String method;
	private String service;

	public AjaxRequest(String service, String method, JSONObject data) {
		this.data = data;
		this.method = method;
		this.service = service;
	}

	public void send(AjaxRequestCallback callback) {
		this.callback = callback;
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "/cmd");
		rb.setHeader("Content-Type", "application/x-www-form-urlencoded");

		StringBuilder sb = new StringBuilder();
		sb.append(JsonRpcConstants.METHOD);
		sb.append("=");
		sb.append(service + "." + method);

		if (data != null) {
			String dataAsJson = data.toString();
			sb.append("&");
			sb.append(JsonRpcConstants.DATA);
			sb.append("=");
			sb.append(dataAsJson);
		}

		rb.setRequestData(sb.toString());
		rb.setCallback(this);
		try {
			rb.send();
		} catch (RequestException e) {
			callback.onRequestError(e);
		}
	}

	@Override
	public void onResponseReceived(Request request, Response response) {
		String responseText = response.getText();

		if (UIHelper.isEmpty(responseText)) {
			callback.onResponse(null);
			return;
		}

		JSONObject json = null;
		try {
			 json = JSONHelper.getJsonFromString(responseText);
		} catch (Exception e) {
			callback.onResponseParseError(e);
		}

		if (json == null) {
			callback.onResponse(null);
			return;
		}

		String errorKey = JSONHelper.getString(json, JsonRpcConstants.ERROR);
		if (!UIHelper.isEmpty(errorKey)) {
			callback.onApplicationError(errorKey);
			return;
		}

		JSONObject result = JSONHelper.getJsonObject(json, JsonRpcConstants.DATA);
		callback.onResponse(result);
	}

	@Override
	public void onError(Request request, Throwable exception) {
		callback.onGenericRequestError(request, exception);
	}

}
