package com.aaa.gomarket.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.aaa.gomarket.holder.BaseHolder;
import com.aaa.gomarket.holder.MoreHolder;
import com.aaa.gomarket.util.UIUtil;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
	private BaseHolder holder;
	private MoreHolder moreHolder;
	private List<T> datas;

	public MyBaseAdapter(List<T> datas) {
		super();
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size()+1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	//条目类型的数量,默认值是1.
    @Override
    public int getViewTypeCount() {
    	// TODO Auto-generated method stub
    	return super.getViewTypeCount()+1;
    }
    
    @Override
    public int getItemViewType(int position) {
    	if (position==getCount()-1) {
			return 1;
		}
    	return 0;
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			//最后一条，getItemViewType(position)返回值是1,显示加载更多条目
			if (getItemViewType(position)==1) {
				holder = getMoreHolder();
			}else{
				holder = getHolder();
			}
			
		} else {
			holder = (BaseHolder) convertView.getTag();
		}
		
		if (getItemViewType(position)==0) {
			// 把数据填充到holder中条目view
			holder.setData(datas.get(position));
		}
		

		return holder.getConvertView();
	}

	// 具体Holder有各个子类实现
	public abstract BaseHolder<T> getHolder();
	// MoreHoler对所有listview都一样,不用抽象了
		public  MoreHolder  getMoreHolder(){
			if (moreHolder==null) {
				moreHolder=new  MoreHolder(this);
			}
			 return  moreHolder;
		}

	// 加载更多的具体数据
	public abstract List<T> loadMoreData();

	public void loadDataMore() {
          new  Thread(){
        	  public void run() {
        		 final List<T>  rs=loadMoreData();
        		 //根据返回的数据,决定加载更多条目接下来显示什么
        		  UIUtil.runInMainThread(new  Runnable() {
					
					@Override
					public void run() {
						if (rs!=null&&rs.size()==0) {
							if (rs.size()==20) {
								moreHolder.setData(2);
							}
							if (rs.size()<20) {
								moreHolder.setData(1);
							}
						}else{
							moreHolder.setData(0);
						}
						//追加到原数据集合
						datas.addAll(rs);
						notifyDataSetChanged();
					}
				});
        	  };
          }.start();
	}

}
