package com.example.android_base_framework;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import com.android.base.framwork.net.NetManager;
import com.android.base.framwork.net.RequestHandlerListener;
import com.android.base.framwork.net.volley.JsonRequest;
import com.android.volley.Request.Method;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
@EActivity
public class MainActivity extends Activity {
	NetManager mNetManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNetManager = NetManager.init(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Click
	void btnClicked(){
		String url = "http://sso.jrj.com.cn/sso/m/userDevice.jsp?UserIDs=140619010046123662";
		JsonRequest<Object> request = new JsonRequest<Object>(Method.GET, url, new RequestHandlerListener() {
			
			@Override
			public void onSuccess(String id, Object data) {
				System.out.println("onSuccess:"+id+" "+data);
			}
			
			@Override
			public void onStart(String id) {
				System.out.println("onStart:"+id);
			}
			
			@Override
			public void onFailure(String id, int code, String str) {
				System.out.println("onFailure:"+str);
			}
			
			@Override
			public void onEnd(String id) {
				System.out.println("onEnd:"+id);
				
			}
		}, Object.class);
		mNetManager.send(request);
	}
}
