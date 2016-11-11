package com.itheima.rbclient.adapter;

import com.itheima.rbclient.holder.BaseHolder;
import com.itheima.rbclient.holder.HotproductHolder;

import java.util.ArrayList;

/**
 * Created by yt on 2016/8/7.
 */
public class HotproductAdapter extends BasicAdapter {
    public HotproductAdapter(ArrayList list) {
        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        return new HotproductHolder();
    }
}
