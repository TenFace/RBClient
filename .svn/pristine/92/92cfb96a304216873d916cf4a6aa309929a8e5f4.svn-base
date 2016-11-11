package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.rbclient.R;
import com.itheima.rbclient.adapter.BasicAdapter;
import com.itheima.rbclient.util.LogUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yt on 2016/8/7.
 */
public abstract class BaceFragment extends BaseFragment implements HttpLoader.HttpListener,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private int page=0;
    private int pageNum=10;
    public List oldlist;
    public List list  ;
    public ListView listView;
    public PullToRefreshListView refreshListView;
    public BasicAdapter adapter;
    public String SonTittle;
    public TextView tv_tettle;
    boolean sale,price,comment,shelves=true;
    public View view;
    private TextView tv_back;
    public RadioGroup radioGroup;
    public HttpParams params ;
    public int SonrequestCode;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = innitView();
        listView.setOnItemClickListener(this);
        return view;
    }

    private View innitView() {
        list=new ArrayList();
        view = View.inflate(getActivity(), R.layout.hotprodctfragment, null);
        tv_tettle = (TextView) view.findViewById(R.id.tv_tettle);
        tv_back = (TextView) view.findViewById(R.id.tv_back);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_select);
        RadioButton radioButton = (RadioButton) view.findViewById(R.id.rb_sales);
        SonRefreshView();
        tv_tettle.setText(SonTittle);
        radioGroup.setOnCheckedChangeListener(this);
        tv_back.setOnClickListener(this);
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_pulltorefresh);
        listView = refreshListView.getRefreshableView();
        setRefreshMode();
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            /**
             * 下拉刷新和上拉加载更多都会执行该方法，
             *
             * @param refreshView
             */
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // 直接调用
                requestNetData();
            }
        });
        radioButton.setChecked(true);
        return view;
    }

    abstract void SonRefreshView() ;

    protected void setRefreshMode() {
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);// 设置既可以上拉也可以下拉
    }
    abstract void requestNetData() ;


    public void requestseccseful() {
        list.addAll(oldlist);
        listView.setAdapter(adapter);
        listView.setSelection(list.size() - oldlist.size());
        adapter.notifyDataSetChanged();
        params=null;
    }




    @Override
    public void onClick(View v) {
        swichToChildFragment(new HomeFragment(), false);
    }
    /***
     *按钮改变监听器
     */

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        LogUtil.d(this,"默认按钮");
        params = new HttpParams();
        params.put("page",""+page);
        params.put("pageNum",""+pageNum);
        switch (checkedId){
            case R.id.rb_sales:
                params.put("orderby","saleDown");
                break;
            case R.id.rb_price:
                if(price){
                    params.put("orderby","priceDown");
                }else{
                    params.put("orderby","priceUp");
                }
                price= !price;
                break;
            case R.id.rb_praise:
                params.put("orderby","priceUp");
                break;
            case R.id.rb_time:

                params.put("orderby","shelvesDown");

                break;
        }
        requestNetData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Sononitemclick(  position);
    }

    protected abstract void Sononitemclick(int position);

}