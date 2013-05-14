package com.lq.activity;

import java.lang.ref.WeakReference;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

public class SplashActivity extends FragmentActivity {

	private MyHandler mHandler = new MyHandler(this);
	private final int mDelayMillis = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		mHandler.sendEmptyMessageDelayed(0, mDelayMillis);
	}

	@Override
	public void onBackPressed() {
		// 什么也不做，在欢迎界面禁止用户回退
	}

	private static class MyHandler extends Handler {
		// 使用弱引用，避免Handler造成的内存泄露(Message持有Handler的引用，内部定义的Handler类持有外部类的引用)
		WeakReference<SplashActivity> mFragmentWeakReference = null;
		SplashActivity mActivity = null;

		public MyHandler(SplashActivity a) {
			mFragmentWeakReference = new WeakReference<SplashActivity>(a);
			mActivity = mFragmentWeakReference.get();
		}

		@Override
		public void handleMessage(Message msg) {
			mActivity.startActivity(new Intent(mActivity,
					MainContentActivity.class));
			mActivity.finish();
		}
	}
}