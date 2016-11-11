package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.holder.ProductDetailHolder;

/**
 * Created by abc on 2016/8/6.
 * 这是点击购物车后跳过来的Fragment
 */
public class ColorAndSizeFragment extends BaseFragment{

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(App.appliction, R.layout.popupwindow_layout,null);
        ProductDetailHolder ph = new ProductDetailHolder(App.appliction);
        ph.setAddMarketCartListener(new ProductDetailHolder.OnAddMarketCartListener() {
            @Override
            public void onClick() {
                Toast.makeText(App.appliction,"dfdfdf",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
