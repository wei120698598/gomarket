package com.aaa.gomarket.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

import com.aaa.gomarket.BaseActivity;

public class PagerTab extends ViewGroup{
	private  BaseActivity  activity;
	private ViewPager  vp;
	private int   tabCount;//子view数量
	private  int  TABTEXTCOLOR=0xff000000;
	private  int  TABTEXTSIZE=25;
	private Paint paint;
	/**
	 * pagerTab监听器
	 */
    private  OnPageChangeListener  delegateListener;
	



	public void setDelegateListener(OnPageChangeListener delegateListener) {
		this.delegateListener = delegateListener;
	}

	public PagerTab(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public PagerTab(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		if (context  instanceof  BaseActivity) {
			activity=(BaseActivity) context;
		}
		initView();
	}
     private void initView() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(0xffbb3344);
		paint.setStrokeWidth(2);
		
	}

	/**
      * 绑定viewpager
      */
	public void  setViewPager(final ViewPager vp){
		this.vp=vp;
		this.vp.setOnPageChangeListener(new  VPListener());
		tabCount=vp.getAdapter().getCount();
		for (int i = 0; i < tabCount; i++) {
			addTab(i,vp.getAdapter().getPageTitle(i).toString());
		}
		//监听view树的布局变化了
		final ViewTreeObserver  vtobserver=getViewTreeObserver();
		if (vtobserver!=null) {
			vtobserver.addOnGlobalLayoutListener(new  OnGlobalLayoutListener() {
				
				@Override
				public void onGlobalLayout() {
					//只执行一次.因为随后都有tab监听器执行
					getViewTreeObserver().removeGlobalOnLayoutListener(this);
					if (delegateListener!=null) {
						delegateListener.onPageSelected(0);
					}
					
				}
			});
		}
		
//		
	}
	/**
	 * 添加tab
	 * @param i
	 * @param string
	 */
	private void addTab(final int i, String title) {
		TextView  tab=new TextView(activity);
		tab.setText(title);
		tab.setTextColor(TABTEXTCOLOR);
		tab.setTextSize(TABTEXTSIZE);
		tab.setGravity(Gravity.CENTER);
		LayoutParams  param=new  LayoutParams(-2,-2);
		tab.setLayoutParams(param);
		tab.setFocusable(true);
		//注册监听器
		tab.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				vp.setCurrentItem(i);
				
			}
		});
		addView(tab, i);
		
	}
	/**
	 * 计算view尺寸
	 */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	//组件自身的尺寸
    	int  selfWidth=MeasureSpec.getSize(widthMeasureSpec)+getPaddingLeft()+getPaddingRight();
    	int  selfheight=MeasureSpec.getSize(heightMeasureSpec)+getPaddingTop()+getPaddingBottom();
    	//宽高模式
    	
    	int  widthMode=MeasureSpec.getMode(widthMeasureSpec);
    	int  heightMode=MeasureSpec.getMode(heightMeasureSpec);
    	//所有子view 的宽度之和
    	  int  totalWidth=0;
    	  int  heighttest=0;
    	for (int i = 0; i < tabCount; i++) {
			final  View child=getChildAt(i);
			//子view的测量说明
			int  childWidthMeasureSpec;
			int  childHeightMeasureSpec;
			
			LayoutParams  param=child.getLayoutParams();
			if (param.width==LayoutParams.MATCH_PARENT) {
				childWidthMeasureSpec=MeasureSpec.makeMeasureSpec(selfWidth, MeasureSpec.EXACTLY);
				
			} else if (param.width==LayoutParams.WRAP_CONTENT){
				childWidthMeasureSpec=MeasureSpec.makeMeasureSpec(selfWidth, MeasureSpec.AT_MOST);
			}else{
				childWidthMeasureSpec=MeasureSpec.makeMeasureSpec(param.width, MeasureSpec.EXACTLY);
			}
			
			if (param.height==LayoutParams.MATCH_PARENT) {
				childHeightMeasureSpec=MeasureSpec.makeMeasureSpec(selfheight, MeasureSpec.EXACTLY);
				
			} else if (param.height==LayoutParams.WRAP_CONTENT){
				childHeightMeasureSpec=MeasureSpec.makeMeasureSpec(selfheight, MeasureSpec.AT_MOST);
			}else{
				childHeightMeasureSpec=MeasureSpec.makeMeasureSpec(param.height, MeasureSpec.EXACTLY);
			}
			
			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
			int  childWidth=child.getMeasuredWidth();
			int  childHeight=child.getMeasuredHeight();
			totalWidth=totalWidth+childWidth;
			heighttest=selfheight;
		}
    	//均分组件的宽度
    	if (totalWidth<selfWidth) {
    		int  splitWidth=selfWidth/tabCount;
    		for (int i = 0; i < tabCount; i++) {
				final  View  child=getChildAt(i);
			int	childWidthMeasureSpec=MeasureSpec.makeMeasureSpec(splitWidth, MeasureSpec.EXACTLY);
			int	childheightMeasureSpec=MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), MeasureSpec.EXACTLY);
			child.measure(childWidthMeasureSpec, childheightMeasureSpec);
			}
			
		}else{
			
		}
    	setMeasuredDimension(selfWidth, heighttest);
    	
    	}
    /**
     * 给子view布局
     */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed) {
			//根据组件的左上右下计算子view 的位置
			int  left=l;
			int  height=b-t;
			for (int i = 0; i < tabCount; i++) {
				final View  child=getChildAt(i);
				int  top=(height-child.getMeasuredHeight())/2;
				int  right=left+child.getMeasuredWidth();
				child.layout(left, top, right, b);
				left=right;
			}
		}
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		
		for (int i = 0; i < tabCount; i++) {
			final View  child=getChildAt(i);
			canvas.drawLine(child.getRight(), 10, child.getRight(), child.getMeasuredHeight()+10, paint);
		}
	}
    //viewpage 切换监听器
	class VPListener  implements  OnPageChangeListener{

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		
		delegateListener.onPageSelected(arg0);
		
	}
	   
   }
	
}










