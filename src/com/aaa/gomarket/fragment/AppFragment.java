package com.aaa.gomarket.fragment;

import android.view.View;
import android.widget.TextView;

import com.aaa.gomarket.view.LoadPager.ResultState;

public class AppFragment extends BaseFragment {

	@Override
	public View createSuccessView() {
		// TODO Auto-generated method stub
		TextView  tv=new  TextView(getActivity());
		tv.setText("AppFragment");
		tv.setTextSize(30);
		return tv;
	}

	@Override
	public ResultState loadData() {
		// TODO Auto-generated method stub
		return ResultState.STATE_ER;
	}

	


}

