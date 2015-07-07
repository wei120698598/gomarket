package com.aaa.gomarket.holder;

import android.view.View;
/**
 * 
 * 获取条目的View，并且给View填充数据.最后返回该view
 *
 * @param <T>
 */
public abstract class BaseHolder <T>
{
	private View  convertView;
	private T  data;
	
	
	public  BaseHolder(){
		convertView=intiView();
    	convertView.setTag(this);
    }
	public void setData(T data) {
		this.data = data;
		refreshView();
	}
	public T getData() {
		return data;
	}
	
    //xml————>view
	public abstract View intiView() ;
	//填充数据
	public abstract  void  refreshView();
	//返回填充了数据的view，显示在listview中
	public  View  getConvertView(){
		return  convertView;
	}
}












