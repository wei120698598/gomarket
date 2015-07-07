package com.aaa.gomarket.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;

public class UIUtil {
	public static Context getContext() {
		return BaseApplication.getContext();
	}

	public static Handler getHandler() {
		return BaseApplication.getHand();
	}

	public static Thread getMainThread() {
		return BaseApplication.getMainThread();
	}

	public static int getMainThreadId() {
		return BaseApplication.getMainThreadId();
	}
	//资源文件夹
	public  static  Resources  getResources(){
		return  getContext().getResources();
	}
	//字符串
	public  static  String  getString(int  id){
		return getResources().getString(id);
	}
	//字符串数组
		public  static  String[]  getStringArray(int  id){
			return getResources().getStringArray(id);
		}
	//图片等
		public  static  Drawable  getDrawalbe(int  id){
			return getResources().getDrawable(id);
		}
		//提取布局文件
		public  static  View  getLayout(int id){
			return  View.inflate(getContext(), id, null);
		}
		//是否运行在主线程中
		public static boolean  inRunInMainThread(){
			return  Process.myTid()==getMainThreadId();
		}
		//将操作放入主线程运行
		public static void  runInMainThread(Runnable  r){
			if (inRunInMainThread()) {
				r.run();
			}else{
				getHandler().post(r);
			}
		}
}












