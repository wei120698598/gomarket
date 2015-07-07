package com.aaa.gomarket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaa.gomarket.util.UIUtil;
import com.aaa.gomarket.view.LoadPager;
import com.aaa.gomarket.view.LoadPager.ResultState;
public abstract class BaseFragment extends Fragment {
	private LoadPager loadpager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/**
		 * 1.loadingView 2.errorView 3.emptyView 4.successView
		 */
		loadpager = new LoadPager(UIUtil.getContext()) {

			@Override
			public ResultState loadData() {
				// TODO Auto-generated method stub
				return BaseFragment.this.loadData();
			}

			@Override
			public View createSuccessView() {
				// TODO Auto-generated method stub
				return BaseFragment.this.createSuccessView();
			}
		};
		return loadpager;
	}

	public abstract View createSuccessView();
	public abstract ResultState  loadData();
	public void fillDataAndShow() {
		System.out.println("---"+loadpager);
		if (loadpager!=null) {
			loadpager.fillAndShow();
		}

	}
}
