package com.android.base.framwork.net;

import java.util.HashMap;
import java.util.Map;

public class NetRequestingManager {
	private Map<Integer,Integer> mMapCount;
	public NetRequestingManager(){
		mMapCount = new HashMap<Integer, Integer>();
	}
	public void addDisplay(RequestingDisplay display){
		int key = display.hashCode();
		if(mMapCount.containsKey(key)){
			mMapCount.put(key, mMapCount.get(key)+1);
		}else{
			mMapCount.put(key, 1);
		}
	}
	public void deleteDisplay(RequestingDisplay display){
		
	}
	
	public interface RequestingDisplay{
		public void show();
		public void hide();
	}
}
