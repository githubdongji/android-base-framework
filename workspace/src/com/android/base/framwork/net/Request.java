package com.android.base.framwork.net;

import java.util.Map;

public abstract class Request<T> {
	
	public interface Method {
    int DEPRECATED_GET_OR_POST = -1;
    int GET = 0;
    int POST = 1;
    int PUT = 2;
    int DELETE = 3;
}
	private RequestHandlerListener listener;
	protected int method;
	protected String url;
	private String id;
	protected Map<String, String> params;
	public Request(int method,String url,RequestHandlerListener listener){
		this.method = method;
		this.url = url;
		this.listener = listener;
		init();
	}
	public Request(int method,String url,Map<String, String> params,RequestHandlerListener listener){
		this.method = method;
		this.url = url;
		this.listener = listener;
		this.params=params;
		init();
	}
	protected abstract void init();
	public String onStart(){
		id = java.util.UUID.randomUUID().toString();
		if(listener!=null){
			listener.obtainMessage(RequestHandlerListener.MSG_START,this).sendToTarget();
		}
		return id;
	}
	public void onSuccess(T data){
		if(listener!=null){
			listener.obtainMessage(RequestHandlerListener.MSG_SUCCESS,data).sendToTarget();
		}
	}
	public void onFailure(int code,String str){
		if(listener!=null){
			listener.obtainMessage(RequestHandlerListener.MSG_FAILURE,code,-1,str).sendToTarget();
		}
	}
	public void onEnd(){
		if(listener!=null){
			listener.obtainMessage(RequestHandlerListener.MSG_END).sendToTarget();
		}
	}
	public void cancel(){
		if(listener!=null){
			listener.setCancel(true);
		}
	}
	
	public abstract Object getTargetRequest();
	public abstract void setTag(Object obj);
	public String getId(){
		return id;
	}
}
