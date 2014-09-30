package com.android.base.framwork.net;

import java.util.HashMap;
import java.util.Map;

public class NetRequestingManager {
	private Map<RequestingDisplay,Integer> mMapCount;
	
	public NetRequestingManager(){
		mMapCount = new HashMap<RequestingDisplay, Integer>();
	}
	public void showDisplay(RequestingDisplay display){
		if(display==null) return;
		if(mMapCount.containsKey(display)){
			mMapCount.put(display, mMapCount.get(display)+1);
		}else{
			mMapCount.put(display, 1);
			display.show();
		}
	}
	public void hideDisplay(RequestingDisplay display){
		if(display==null) return;
		if(mMapCount.containsKey(display)){
			int count  = mMapCount.get(display);
			count--;
			if(count<=0){
				display.hide();
			}
		}
	}
	
	public interface RequestingDisplay{
		public void show();
		public void hide();
	}
}
