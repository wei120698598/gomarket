package com.aaa.gomarket.util;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

//全局属性
public class BaseApplication extends Application {
	private static Context context;

	private static Handler hand;
	private static Thread mainThread;
	private static int mainThreadId;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getApplicationContext();
		hand = new Handler();
		mainThread = Thread.currentThread();
		mainThreadId = Process.myTid();
	}

	public static Context getContext() {
		return context;
	}

	public static Handler getHand() {
		return hand;
	}

	public static Thread getMainThread() {
		return mainThread;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}
}
