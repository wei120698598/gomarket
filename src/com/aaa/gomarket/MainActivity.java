package com.aaa.gomarket;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import android.widget.TextView;

import com.aaa.gomarket.fragment.BaseFragment;
import com.aaa.gomarket.fragment.FragmentFactory;
import com.aaa.gomarket.util.UIUtil;
import com.aaa.gomarket.view.PagerTab;

public class MainActivity extends BaseActivity {
	private ActionBar abr;
	private DrawerLayout drawer;
	private ActionBarDrawerToggle abt;
	private ViewPager vp;
	private ArrayList<TextView> datas = new ArrayList<TextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initActionBar();
		// initData();
		initView();
	}

	private void initView() {
		drawer = (DrawerLayout) findViewById(R.id.drawer);
		// 绑定actionbar和drawer
		abt = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open,
				R.string.drawer_close);
		// synchronized（同步）
		abt.syncState();
		drawer.setDrawerListener(abt);
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(new MainAdapter(getSupportFragmentManager()));
		PagerTab pagetab = (PagerTab) findViewById(R.id.pagertab);
		pagetab.setViewPager(vp);
		pagetab.setDelegateListener(new  OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				
				BaseFragment  fragment=FragmentFactory.createFragment(arg0);
				
				fragment.fillDataAndShow();
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	// public void initData(){
	// try
	// {
	// TextView a1=new TextView(this);
	// a1.setText("aaaaaaaa");
	// TextView a2=new TextView(this);
	// a2.setText("bbbbbbbbbbb");
	// TextView a3=new TextView(this);
	// a3.setText("cccccccc");
	// TextView a4=new TextView(this);
	// a4.setText("ddddddddd");
	//
	// datas.add(a1);
	// datas.add(a2);
	// datas.add(a3);
	// datas.add(a4);
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }
	// class MainAdapter extends PagerAdapter{
	//
	// @Override
	// public int getCount() {
	// // TODO Auto-generated method stub
	// return datas.size();
	// }
	//
	// @Override
	// public boolean isViewFromObject(View arg0, Object arg1) {
	// // TODO Auto-generated method stub
	// return arg0==arg1;
	// }
	// @Override
	// public Object instantiateItem(ViewGroup container, int position) {
	// container.addView(datas.get(position));
	// return datas.get(position);
	// }
	// @Override
	// public void destroyItem(ViewGroup container, int position, Object object)
	// {
	// // TODO Auto-generated method stub
	// container.removeView(datas.get(position));
	// }
	// @Override
	// public CharSequence getPageTitle(int position) {
	// // TODO Auto-generated method stub
	// return "xxx"+position;
	// }
	// }

	class MainAdapter extends FragmentPagerAdapter {
         String stringArrays[];
		public MainAdapter(FragmentManager fm) {
			super(fm);
			stringArrays=UIUtil.getStringArray(R.array.tab_names);
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return FragmentFactory.createFragment(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return stringArrays.length;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return stringArrays[position];
		}

	}

	private void initActionBar() {
		abr = getSupportActionBar();
		abr.setTitle("googlemarket");
		// abr.setDefaultDisplayHomeAsUpEnabled(true);
		abr.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			abt.onOptionsItemSelected(item);
			break;

		default:
			break;
		}
		return true;
	}
}
