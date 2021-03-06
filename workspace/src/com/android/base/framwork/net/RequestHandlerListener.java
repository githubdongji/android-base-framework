package com.android.base.framwork.net;


import android.os.Handler;
import android.os.Message;

public abstract class RequestHandlerListener extends Handler implements RequestListener{
	public static final int MSG_START=0;
	public static final int MSG_SUCCESS=1;
	public static final int MSG_FAILURE=2;
	public static final int MSG_END=3;
	
	private boolean isCancel=false;
	private String id;
	private Request  mRequest;
	@Override
	public void handleMessage(Message msg) {
		if(isCancel)return;
		switch(msg.what){
		case MSG_START:
			if(msg.obj!=null&&msg.obj instanceof Request){
				mRequest = (Request) msg.obj;
				id = mRequest.getId();
				onStart((Request)msg.obj);
			}
			break;
		case MSG_SUCCESS:
			if(msg.obj!=null){
				onSuccess(id,msg.obj);
			}
			break;
		case MSG_FAILURE:
			if(msg.obj!=null&&msg.obj instanceof String){
				onFailure(id,msg.arg1,(String)msg.obj);
			}
			break;
		case MSG_END:
			onEnd(mRequest);
			break;
		}
	}
	@Override
	public void onFailure(String id, int code, String str) {
		
	}
	@Override
	public void onStart(Request request) {
		
	}
	@Override
	public void onEnd(Request  request) {
		
	}
	public void setCancel(boolean isCancel){
		this.isCancel = isCancel;
	}
	

}
