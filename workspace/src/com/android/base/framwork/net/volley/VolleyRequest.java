package com.android.base.framwork.net.volley;

import java.util.Map;

import com.android.base.framwork.net.Request;
import com.android.base.framwork.net.RequestHandlerListener;
import com.android.volley.VolleyError;

public abstract class VolleyRequest<T> extends Request<T>{

	public VolleyRequest(int method, String url, RequestHandlerListener listener) {
		super(method, url, listener);
	}
	public VolleyRequest(int method, String url,Map<String, String> params, RequestHandlerListener listener) {
		super(method, url,params, listener);
	}
	
	protected String getErrorStr(VolleyError error){
		return VolleyErrorHelper.getMessage(error);
	}
}
