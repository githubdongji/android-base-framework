package com.android.base.framwork.net;

import android.content.Context;

public class NetManager {
	public NetManager(Context ctx){
		
	}
	public static NetManager init(Context ctx){
		return new NetManager(ctx);
	}
}
