package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.ProductListAdapter;
import com.itheima.rbclient.bean.ClassificationResponse;
import com.itheima.rbclient.bean.MyProductList;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class MyProductListFragment extends BaseFragment implements HttpLoader.HttpListener {
    private GridView gridView;
    private MyProductList myProductList;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myproductlist,null);
        gridView = (GridView) view.findViewById(R.id.myproductlist_gv);
        initData();
        return view;
    }

    private void initData() {
        App.HL.get(RBConstants.URL_SERVER+"limitbuy",null,MyProductList.class,45,this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        myProductList = (MyProductList) response;
        gridView.setAdapter(new ProductListAdapter(myProductList.getProductList()));
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
