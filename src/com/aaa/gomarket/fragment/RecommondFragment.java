package com.aaa.gomarket.fragment;

import android.view.View;
import android.widget.TextView;

import com.aaa.gomarket.util.UIUtil;
import com.aaa.gomarket.view.LoadPager.ResultState;

public class RecommondFragment extends BaseFragment {

	@Override
	public View createSuccessView() {
		TextView  tv=new  TextView(getActivity());
		tv.setText("RecommondFragment");
		tv.setTextSize(30);
		return tv;
	}

	@Override
	public ResultState loadData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}