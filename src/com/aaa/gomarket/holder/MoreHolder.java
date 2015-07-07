package com.aaa.gomarket.holder;

import android.R;
import android.view.View;
import android.widget.LinearLayout;

import com.aaa.gomarket.adapter.MyBaseAdapter;
import com.aaa.gomarket.util.UIUtil;
//定义加载更多条目类型的显示
public class MoreHolder extends BaseHolder<Integer>{

	private LinearLayout normalLoad;
	private LinearLayout errorLoad;
	//三种结果,显示三种view
	private  static int  load_error=0;
	private  static int  load_empty=1;
	private  static int  load_success=2;
	private MyBaseAdapter  adapter;

	public MoreHolder(MyBaseAdapter  adapter) {
		super();
		this.adapter=adapter;
		setData(load_success);
	}

	@Override
	public View intiView() {
		View  view=UIUtil.getLayout(R.layout.loaddatamore);
		normalLoad = (LinearLayout) view.findViewById(R.id.loadmore_loading);
		errorLoad = (LinearLayout) view.findViewById(R.id.loadmore_error);
		return view;
	}

	@Override
	public void refreshView() {
		int  data=getData();
		if (data==load_error) {
			normalLoad.setVisibility(View.GONE);
			errorLoad.setVisibility(View.VISIBLE);
		}
		if (data==load_empty) {
			normalLoad.setVisibility(View.GONE);
			errorLoad.setVisibility(View.GONE);
		}
		if (data==load_success) {
			normalLoad.setVisibility(View.VISIBLE);
			errorLoad.setVisibility(View.GONE);
		}
		
	}
	//当看见加载更多条目的时候,去从服务器拉数据
	@Override
	public View getConvertView() {
		if (adapter!=null) {
			adapter.loadDataMore();
		}
		return super.getConvertView();
	}

}











