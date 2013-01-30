package com.ss.common.gwt.jsonrpc.client;

public interface JsonRpcRequestLifeCycleListener {

	void beforeCall();

	void afterCall();

}
