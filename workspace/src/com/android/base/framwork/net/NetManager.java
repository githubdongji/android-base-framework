package com.android.base.framwork.net;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class NetManager {
	RequestQueue mQueue ;
	Context ctx;
	public NetManager(Context ctx){
		this.ctx = ctx;
		mQueue = Volley.newRequestQueue(ctx); 
	}
	public static NetManager init(Context ctx){
		return new NetManager(ctx);
	}
	public void send(Request request){
		Object obj = request.getTargetRequest();
		if(obj!=null&&obj instanceof  com.android.volley.Request){
			mQueue.add((com.android.volley.Request) obj);
			request.onStart();
		}
	}
	public void cancel(Request request){
		request.cancel();
	}
	public void cancelAll(){
		mQueue.cancelAll(ctx);
	}
	public void cancelByTag(Object tag){
		mQueue.cancelAll(tag);
	}
}
