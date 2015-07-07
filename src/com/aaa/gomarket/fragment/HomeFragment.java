package com.aaa.gomarket.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.aaa.gomarket.adapter.MyBaseAdapter;
import com.aaa.gomarket.holder.BaseHolder;
import com.aaa.gomarket.holder.HomeHolder;
import com.aaa.gomarket.view.LoadPager.ResultState;

public class HomeFragment extends BaseFragment {

	private List<String> datas = new ArrayList<String>();

	@Override
	public View createSuccessView() {
		ListView listv = new ListView(getActivity());
		listv.setAdapter(new HomeAdapter(datas));
		return listv;
	}

	public void initData() {
		
		for (int i = 0; i < 30; i++) {
			datas.add("aaa" + i);
		}
	}
    class  HomeAdapter  extends  MyBaseAdapter<String>{

		public HomeAdapter(List<String> datas) {
			super(datas);
			// TODO Auto-generated constructor stub
		}

		@Override
		public BaseHolder<String> getHolder() {
			// TODO Auto-generated method stub
			return new   HomeHolder();
		}

		@Override
		public List<String> loadMoreData() {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			List<String>  rs=new  ArrayList<String>();
			for (int i = 30; i < 50; i++) {
				rs.add("xxx"+i);
			}
			return rs;
		}
    	
    }
	

	@Override
	public ResultState loadData() {
		initData();
		return ResultState.STATE_S;
	}

}
