package com.itheima.rbclient.adapter;

import com.itheima.rbclient.App;
import com.itheima.rbclient.holder.BaseHolder;
import com.itheima.rbclient.holder.FlashSaleHolder;
import com.itheima.rbclient.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by yt on 2016/8/6.
 */
public class FlashSaleAdapter extends BasicAdapter {
    public FlashSaleAdapter(ArrayList list) {

        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        LogUtil.e(App.appliction.getApplicationContext(),"holder");
        return new FlashSaleHolder();
    }
}
