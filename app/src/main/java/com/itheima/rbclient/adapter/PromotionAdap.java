package com.itheima.rbclient.adapter;

import com.itheima.rbclient.holder.BaseHolder;
import com.itheima.rbclient.holder.PromotionHolder;

import java.util.ArrayList;

/**
 * Created by yt on 2016/8/4.
 */
public class PromotionAdap extends BasicAdapter {
    public PromotionAdap(ArrayList list) {
        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        return new PromotionHolder();
    }
}
