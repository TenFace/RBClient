package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.Favorite;
import com.itheima.rbclient.holder.FavoriteHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by lenovo on 2016/8/3.
 */
public class FavoriteAdapter extends AbsBaseAdapter<Favorite.ProductListBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public FavoriteAdapter(List<Favorite.ProductListBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoriteHolder(App.appliction);
    }
}
