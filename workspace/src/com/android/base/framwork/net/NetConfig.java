package com.android.base.framwork.net;

import java.util.HashMap;
import java.util.Map;

public class NetConfig {
	private static final int connectionTimeout = 15000;
//	private static final int socketTimeout = 15000;
	private static final String HOST = "172.16.2.223";
	
	public static int getConnectionTimeout(){
		return connectionTimeout;
	}
	
	public static final String DEVID = "devid";
	public static final String PALTID = "paltid";
	public static final String APPVER = "appver";
	public static final String MODEL = "model";
	public static final String LOCALIZEDMODEL = "localizedModel";
	public static final String SYSTEMNAME = "systemName";
	public static final String SYSTEMVERSION = "systemVersion";
	public static final String PRODUCTID = "productid";
	
	public static Map<String, String> getHeaders(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put(DEVID, "application/json");
		headers.put(PALTID, "application/json");
		headers.put(APPVER, "application/json");
		headers.put(MODEL, "application/json");
		headers.put(LOCALIZEDMODEL, "application/json");
		headers.put(SYSTEMNAME, "application/json");
		headers.put(SYSTEMVERSION, "application/json");
		headers.put(PRODUCTID, "application/json");
		return headers;
	}
}
