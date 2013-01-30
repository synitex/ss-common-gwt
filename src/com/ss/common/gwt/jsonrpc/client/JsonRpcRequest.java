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

/**
 * 
 * 
 * @author sergey.sinica
 * 
 */
public class JsonRpcRequest implements RequestCallback {

	private JsonRpcRequestCallback callback;
	private JSONObject data;
	private String method;
	private String service;

	private String url;
	private JsonRpcRequestLifeCycleListener lifeCycleListener;

	JsonRpcRequest(String service, String method, JSONObject data) {
		this.data = data;
		this.method = method;
		this.service = service;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLifeCycleListener(JsonRpcRequestLifeCycleListener lifeCycleListener) {
		this.lifeCycleListener = lifeCycleListener;
	}

	public void send(JsonRpcRequestCallback callback) {
		if (callback == null) {
			throw new IllegalArgumentException("Callback can not be null!");
		}
		this.callback = callback;

		if (lifeCycleListener != null) {
			lifeCycleListener.beforeCall();
		}

		try {
			sendImpl();
		} finally {
			if (lifeCycleListener != null) {
				lifeCycleListener.afterCall();
			}
		}
	}

	private void sendImpl() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "/" + url);
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
			if (callback != null) {
				callback.onRequestError(e);
			}
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
			callback.onResponseParseError(new RuntimeException("No response json is parsed"));
			return;
		}
		
		JSONObject errorJson = JSONHelper.getJsonObject(json, JsonRpcConstants.ERROR);
		if(errorJson != null) {
			GwtErrorDto errorDto = new GwtErrorDto().fromJson(errorJson);
			callback.onApplicationError(errorDto);
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
