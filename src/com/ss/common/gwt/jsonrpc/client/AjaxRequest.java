package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.ss.common.gwt.jsonrpc.shared.AjaxRequestConstants;
import com.ss.common.gwt.util.client.JSONHelper;
import com.ss.common.gwt.util.client.UIHelper;

public class AjaxRequest implements RequestCallback {

	private AjaxRequestCallback callback;
	private JSONObject data;
	private String method;

	public AjaxRequest(String method, JSONObject data) {
		this.data = data;
		this.method = method;
	}

	public void send(AjaxRequestCallback callback) {
		this.callback = callback;
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "/cmd");
		rb.setHeader("Content-Type", "application/x-www-form-urlencoded");

		StringBuilder sb = new StringBuilder();
		sb.append(AjaxRequestConstants.METHOD);
		sb.append("=");
		sb.append(method);

		if (data != null) {
			String dataAsJson = data.toString();
			sb.append("&");
			sb.append(AjaxRequestConstants.DATA);
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

		String errorKey = JSONHelper.getString(json, AjaxRequestConstants.ERROR);
		if (!UIHelper.isEmpty(errorKey)) {
			callback.onApplicationError(errorKey);
			return;
		}

		JSONObject result = JSONHelper.getJsonObject(json, AjaxRequestConstants.DATA);
		callback.onResponse(result);
	}

	@Override
	public void onError(Request request, Throwable exception) {
		callback.onGenericRequestError(request, exception);
	}

}
