package com.ss.common.gwt.jsonrpc.client;

import com.google.gwt.json.client.JSONObject;

/**
 * Builder used to create {@link JsonRpcRequest} instances.
 * 
 * @author sergey.sinica
 * 
 */
public class JsonRpcRequestBuilder {

	private String url;
	private JsonRpcRequestLifeCycleListener lifeCycleListener;

	/**
	 * {@link JsonRpcRequest} builder.
	 * 
	 * @param url
	 *            if your json-rpc services processor will be exposed by url
	 *            http://mydomain.com/services, when you should pass following
	 *            parameter "services".
	 */
	public JsonRpcRequestBuilder(String url) {
		this.url = url;
	}
	
	public JsonRpcRequestBuilder setLifeCycleListener(JsonRpcRequestLifeCycleListener lifeCycleListener) {
		this.lifeCycleListener = lifeCycleListener;
		return this;
	}

	public void call(String serviceId, String methodId, JSONObject json, JsonRpcRequestCallback callback) {
		JsonRpcRequest req = new JsonRpcRequest(serviceId, methodId, json);
		req.setUrl(url);
		req.setLifeCycleListener(lifeCycleListener);
		req.send(callback);
	}

}
