package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.json.client.JSONObject;

public interface AjaxRequestCallback {

	void onResponse(JSONObject result);

	void onApplicationError(String errorKey);

	void onRequestError(RequestException ex);

	void onGenericRequestError(Request request, Throwable exception);

	void onResponseParseError(Throwable ex);

}
