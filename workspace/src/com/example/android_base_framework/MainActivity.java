package com.example.android_base_framework;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import com.android.base.framwork.net.NetManager;
import com.android.base.framwork.net.NetRequestingManager;
import com.android.base.framwork.net.Request;
import com.android.base.framwork.net.NetRequestingManager.RequestingDisplay;
import com.android.base.framwork.net.RequestHandlerListener;
import com.android.base.framwork.net.volley.JsonRequest;
import com.android.volley.Request.Method;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
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
		for(int i=0;i<2;i++){
			final int ii = i;
		String url = "http://sso.jrj.com.cn/sso/m/userDevice.jsp?UserIDs=140619010046123662";
		JsonRequest<Object> request = new JsonRequest<Object>(Method.GET, url, new RequestHandlerListener() {
			
			@Override
			public void onSuccess(String id, Object data) {
				System.out.println("onSuccess:"+id+" "+data);
			}
			@Override
			public void onStart(Request request) {
				super.onStart(request);
				mRequesting.showDisplay(request);
			}
			
			@Override
			public void onFailure(String id, int code, String str) {
				System.out.println("onFailure:"+str);
			}
			
			@Override
			public void onEnd(final Request request) {
				System.out.println("onEnd");
				mHandler.postDelayed(new Runnable() {
					
					@SuppressWarnings("unchecked")
					@Override
					public void run() {
						System.out.println("onEnd run");
						mRequesting.hideDisplay(request);
					}
				}, 1000*ii);
			}
		}, Object.class);
		mNetManager.send(request);
		}
	}
	Dialog mDialog = null;
	
	Handler mHandler = new Handler();
	private RequestingDisplay mDisplay=new RequestingDisplay() {
		@Override
		public void show() {
			mDialog = new AlertDialog.Builder(MainActivity.this).create();
			mDialog.setTitle("БъЬт");
			mDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					mRequesting.cancel();
				}
			});
			mDialog.show();
		}
		
		@Override
		public void hide() {
			if(mDialog!=null)
			mDialog.hide();
		}
	};
	
	private NetRequestingManager mRequesting = new NetRequestingManager(mDisplay);
}
