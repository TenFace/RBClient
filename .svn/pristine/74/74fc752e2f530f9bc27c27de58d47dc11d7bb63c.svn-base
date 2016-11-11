package com.itheima.rbclient.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.adapter.BigMarketDetailAdapter;
import com.itheima.rbclient.bean.EventBusBean;
import com.itheima.rbclient.bean.ProductDetail;
import com.itheima.rbclient.holder.BigMarketDetailHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 赵志杰 on 2016/8/3.
 * 这是商品详情页面的缩放Fragment
 */
public class BigMarketDetailFragment extends BaseFragment {
    private View rootView;

    @InjectView(R.id.vp_market_big_imag)
    ViewPager vp_market_big_imag;//存放大图的ViewPager

    BigMarketDetailHolder bigMarketDetailHolder;

    private ProductDetail.ProductBean data1;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        //加载一个xml文件(现在fragment中添加一个大布局)
        View bigImageView = View.inflate(App.appliction, R.layout.fragment_market_detail_bigimage, null);
        ButterKnife.inject(this, bigImageView);
        //对ViewPager（显示大图）设置下适配器（参数是：Product的数据data）
        vp_market_big_imag.setAdapter(new BigMarketDetailAdapter(data1));

        bigMarketDetailHolder = new BigMarketDetailHolder(App.appliction);
        //调用holder的bindData方法。
        bigMarketDetailHolder.bindData(data1);
        return bigImageView;
    }

    @Subscribe(sticky = true)
    public void onEvent(EventBusBean event) {
        data1 = event.getData();
    }

}
