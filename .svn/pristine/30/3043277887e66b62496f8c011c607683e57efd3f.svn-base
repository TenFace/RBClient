package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.rbclient.R;
import com.itheima.rbclient.adapter.BasicAdapter;

import org.senydevpkg.net.HttpLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class BaseListFragment<T> extends BaseFragment implements HttpLoader.HttpListener, View.OnClickListener {
	public View view;

	public List oldlist;
	public List list  =new ArrayList();
	public ListView listView;
	public PullToRefreshListView refreshListView;
	public BasicAdapter adapter;

	public TextView tv_home_tille;
	HashMap<String,String > hashMap;
	public TextView tv_home_salespromotion;
	public Button button;


	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		hashMap = sonHashmap();
		  view = innitView();
		bindData();
		return view;
	}

	protected abstract HashMap<String,String> sonHashmap();

	private void bindData() {
		requestNetData();
	}

	private View innitView() {
		view = View.inflate(getActivity(), R.layout.fragment_salespromotion,null);
		tv_home_salespromotion = (TextView) view.findViewById(R.id.tv_home_salespromotion);
		tv_home_salespromotion.setText(hashMap.get("tv_home_salespromotion"));
		button = (Button) view.findViewById(R.id.head_left);
		button.setOnClickListener(this);
		refreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_home_salepromotion);

		listView = refreshListView.getRefreshableView();
		setRefreshMode();
		refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
			/**
			 * 下拉刷新和上拉加载更多都会执行该方法，
			 * @param refreshView
			 */
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// 直接调用
				requestNetData();
			}
		});
		refreshListView.onRefreshComplete();
		return view;
	}

	protected abstract void requestNetData();

	/**
	 * 刷新数据*/



	protected abstract BasicAdapter getAdapter();


	@Override
	public void onGetResponseError(int requestCode, VolleyError error) {

	}
	protected void setRefreshMode() {
		refreshListView.setMode(PullToRefreshBase.Mode.BOTH);// 设置既可以上拉也可以下拉
	}

	@Override
	public void onClick(View v) {
		swichToChildFragment(new HomeFragment(),false);
	}
}
