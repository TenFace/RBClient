package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.CartResponse;
import com.itheima.rbclient.holder.CartHolder;
import com.itheima.rbclient.protocol.RequestProtocol;

import org.senydevpkg.net.resp.IResponse;


public class CartFragment extends BaseFragment {

    private LoadingPage loadingPage;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        final CartHolder cartHolder = new CartHolder(getContext(),this);
        loadingPage = new LoadingPage(App.appliction) {

            @Override
            protected void initDataFromService() {
                new RequestProtocol().doRequest(App.HL, this).setTag(this);
            }

            @Override
            public View createSuccessView() {
                View view = cartHolder.initView();
                return view;
            }

            @Override
            protected void fillData(int requestCode, IResponse response) {
                cartHolder.bindData((CartResponse) response);
            }

        };
        if (loadingPage != null) {
            loadingPage.getData();
        }

        //跳转到结算界面
        cartHolder.getBtn_pay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swichToChildFragment(JiesuanCenterFragment.instantiate(App.appliction,"com.itheima.rbclient.ui.fragment.JiesuanCenterFragment"),true);
            }
        });
        //跳转到编辑界面
        cartHolder.getBtn_setting().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入编辑界面
                    swichToChildFragment(HomeFragment.instantiate(App.appliction, "com.itheima.rbclient.ui.fragment.HomeFragment"),true);
                }
            });
        //跳转到优惠券界面
        cartHolder.getBtn_free().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swichToChildFragment(JiesuanCenterFragment.instantiate(App.appliction,"com.itheima.rbclient.ui.fragment.JiesuanCenterFragment"),true);
            }
        });
        return loadingPage;
    }

}
