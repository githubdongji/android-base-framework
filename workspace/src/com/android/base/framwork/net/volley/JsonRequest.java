package com.android.base.framwork.net.volley;

import java.util.Map;

import org.json.JSONObject;

import com.android.base.framwork.net.NetConfig;
import com.android.base.framwork.net.Request;
import com.android.base.framwork.net.RequestHandlerListener;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

public class JsonRequest<T> extends VolleyRequest<T>{
	private Class<T> jsonClass;
	public JsonRequest(int method, String url, RequestHandlerListener listener,Class<T> jsonClass) {
		super(method, url, listener);
		this.jsonClass = jsonClass;
	}
	public JsonRequest(int method,String url,Map<String, String> params,RequestHandlerListener listener,Class<T> jsonClass) {
		super(method, url,params, listener);
		this.jsonClass = jsonClass;
	}
	JsonObjectRequest jsonObjectRequest ;
	
	protected void init() {
		jsonObjectRequest = new JsonObjectRequest(method, url,null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					Gson json = new Gson();
					T object = json.fromJson(response.toString(), jsonClass);
					onSuccess(object);
					onEnd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},
		new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				onFailure(0,getErrorStr(error));
				onEnd();
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if(params!=null&&params.entrySet().size()>0){
					return params;
				}
				return super.getParams();
			}
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				return NetConfig.getHeaders();
			}
			@Override
			public RetryPolicy getRetryPolicy() {
				RetryPolicy retryPolicy = new DefaultRetryPolicy(NetConfig.getConnectionTimeout(), DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
				return retryPolicy;
			}
		};
	}
	
	public void cancel(){
		super.cancel();
		jsonObjectRequest.cancel();
	}
	@Override
	public Object getTargetRequest() {
		return jsonObjectRequest;
	}
	@Override
	public void setTag(Object obj) {
		jsonObjectRequest.setTag(obj);
	}
	
}
