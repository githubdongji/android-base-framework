package com.android.base.framwork.net;

public interface RequestListener {
	public  void onStart(Request  request);
	public  void onSuccess(String id,Object data);
	public  void onFailure(String id,int code,String str);
	public  void onEnd(Request  request);
}
