package com.android.base.framwork.net;

public interface RequestListener {
	public  void onStart(String id);
	public  void onSuccess(String id,Object data);
	public  void onFailure(String id,int code,String str);
	public  void onEnd(String id);
}
