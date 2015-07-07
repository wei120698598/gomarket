package com.aaa.gomarket.holder;

import android.R;
import android.view.View;
import android.widget.TextView;

import com.aaa.gomarket.util.UIUtil;

public class HomeHolder extends  BaseHolder<String>{

	private TextView tv;
    //xml-->view
	@Override
	public View intiView() {
		View view=UIUtil.getLayout(R.layout.home_item);
		tv = (TextView) view.findViewById(R.id.textView1);
		return view;
	}
    //填充
	@Override
	public void refreshView() {
		String  data=getData();
		tv.setText(data);
		
	}

}
