package com.android.base.framwork.net;

import java.util.ArrayList;
import java.util.List;


public class NetRequestingManager {
	private RequestingDisplay mDisplay;
	private int mCount;
	private List<Request<Object>> mlist;
	public NetRequestingManager(RequestingDisplay display){
		mDisplay=display;
		mCount=0;
		mlist = new ArrayList<Request<Object>>();
	}
	public void showDisplay(Request<Object> request){
		if(mCount==0){
			mDisplay.show();
		}
		mlist.add(request);
		mCount++;
	}
	public void hideDisplay(Request<Object> request){
		if(mCount>0){
			mCount--;
			mlist.remove(request);
			if(mCount<=0){
				mDisplay.hide();
			}
		}
		System.out.println("mCount:"+mCount);
	}
	public void cancel(){
		for(Request<Object> request:mlist){
			request.cancel();
		}
	}
	
	public interface RequestingDisplay{
		public void show();
		public void hide();
	}
}
