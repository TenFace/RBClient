package com.itheima.rbclient.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.rbclient.R;


/**
 * 只是上拉加载ListView
 */
public class OrderRefreshListView extends ListView implements OnScrollListener {

	private View mFooterView;
	private int mFooterViewHeight;
	private int startX;
	private int startY;

	public OrderRefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initFooterView();
	}

	public OrderRefreshListView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public OrderRefreshListView(Context context) {
		this(context, null);
	}


	//初始化脚布局
	private void initFooterView() {
		mFooterView = View.inflate(getContext(),
				R.layout.pull_to_refresh_footer, null);
		addFooterView(mFooterView);

		mFooterView.measure(0, 0);
		mFooterViewHeight = mFooterView.getMeasuredHeight();
		//隐藏脚布局
		mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);

		//设置滑动监听
		setOnScrollListener(this);
	}


	/**
	 * 逻辑为当左右滑动时请求父类拦截触摸事件
	 * @param ev
	 * @return
     */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {


		return super.onTouchEvent(ev);//要返回super, 方便listView原生滑动处理
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = (int) ev.getX();
				startY = (int) ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				int endX = (int) ev.getX();
				int endY = (int) ev.getY();
				int dx = endX - startX;
				int dy = endY - startY;

				if (Math.abs(dx) > Math.abs(dy)){//左右滑动
					//请求父类拦截事件
					getParent().requestDisallowInterceptTouchEvent(false);
				}

				break;
			case MotionEvent.ACTION_UP:

				break;

			default:
				break;
		}
		return super.dispatchTouchEvent(ev);
	}

	//刷新结束,隐藏控件
	public void onRefreshComplete() {
			//隐藏加载更多的控件
			mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
	}

	private OnRefreshListener mListener;

	//设置刷新回调监听
	public void setOnRefreshListener(OnRefreshListener listener) {
		mListener = listener;
	}

	//回调接口,通知刷新状态
	public interface OnRefreshListener {

		//加载更多的回调
		public void onLoadMore();
	}


	//滑动状态发生变化
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE) {//空闲状态
			int lastVisiblePosition = getLastVisiblePosition();//当前显示的最后一个item的位置
			if (lastVisiblePosition == getCount() - 1) {
				System.out.println("到底了...");

				//显示加载中布局
				mFooterView.setPadding(0, 0, 0, 0);
				setSelection(getCount() - 1);//显示在最后一个item的位置(展示加载中布局)

				//加载更多数据
				if (mListener != null) {
					mListener.onLoadMore();
				}
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

	}
}
