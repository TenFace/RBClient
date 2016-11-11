package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.TopicResponse;
import com.itheima.rbclient.holder.TopicHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by xiongmc on 2016/6/7.
 */
public class TopicAdapter extends AbsBaseAdapter<TopicResponse.TopicBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public TopicAdapter(List<TopicResponse.TopicBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicHolder(App.appliction);
    }

}
