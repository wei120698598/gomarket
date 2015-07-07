package com.aaa.gomarket.view;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.aaa.gomarket.util.UIUtil;

//根据联网获取数据的结果显示不同的view

public abstract class LoadPager extends FrameLayout {
	private static int STATE_UNLOAD = 0;
	private static int STATE_LOADING = 1;
	private static int STATE_ERROR = 2;
	private static int STATE_EMPTY = 3;
	private static int STATE_SUCCESS = 4;
	// 当前状态
	private int cur_state;
	private View loadingView;
	private View errorView;
	private View emptyView;
	private View successView;
	LayoutParams param;

	public LoadPager(Context context) {
		super(context);
		param = new LayoutParams(-1, -1);
		initView();
	}

	private void initView() {
		cur_state=STATE_UNLOAD;
		if (loadingView == null) {
			loadingView = UIUtil.getLayout(R.layout.loadingview);
			addView(loadingView, param);
		}
		if (errorView == null) {
			errorView = UIUtil.getLayout(R.layout.errorview);
			addView(errorView, param);
		}
		if (emptyView == null) {
			emptyView = UIUtil.getLayout(R.layout.emptyview);
			addView(emptyView, param);
		}

      showSafePage();
	}

	private void showSafePage() {
		UIUtil.runInMainThread(new  Runnable() {
			
			@Override
			public void run() {
				showPage();
				
			}
		});
		
	}

	private void showPage() {
		
		if (loadingView != null) {
			loadingView
					.setVisibility((cur_state == STATE_UNLOAD || cur_state == STATE_LOADING) ? View.VISIBLE
							: View.GONE);
		}
		if (errorView != null) {
			errorView.setVisibility(cur_state == STATE_ERROR ? View.VISIBLE
					: View.GONE);
		}
		if (emptyView != null) {
			emptyView.setVisibility(cur_state == STATE_EMPTY ? View.VISIBLE
					: View.GONE);
		}
		if (successView == null && cur_state == STATE_SUCCESS) {
			successView = createSuccessView();
			addView(successView, param);
		}
		if (successView != null) {
			successView.setVisibility(cur_state == STATE_SUCCESS ? View.VISIBLE
					: View.GONE);
		}
	}

	public void fillAndShow() {
		if (cur_state==STATE_EMPTY||cur_state==STATE_EMPTY||cur_state==STATE_SUCCESS) {
			cur_state=STATE_UNLOAD;
		}
		if (cur_state == STATE_UNLOAD) {
			new Thread() {
				public void run() {
					final ResultState rs = loadData();
					UIUtil.runInMainThread(new Runnable() {

						@Override
						public void run() {
							if (rs != null) {
								cur_state = rs.getValue();
								showPage();
							}

						}
					});
				};
			}.start();
		}
	}

	public abstract View createSuccessView();

	public abstract ResultState loadData();

	public enum ResultState {
		STATE_ER(2), STATE_E(3), STATE_S(4);
		private int value;

		private ResultState(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

}
