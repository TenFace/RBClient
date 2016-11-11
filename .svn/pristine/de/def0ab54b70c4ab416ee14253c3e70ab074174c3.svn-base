package com.itheima.rbclient.adapter;

import android.view.ViewGroup;

import com.itheima.rbclient.App;
import com.itheima.rbclient.bean.Address;
import com.itheima.rbclient.holder.AddressHolder;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by lenovo on 2016/8/5.
 */
public class AddressAdapter extends AbsBaseAdapter<Address.AddressListBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param data 要显示的数据
     */
    public AddressAdapter(List<Address.AddressListBean> data) {
        super(data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressHolder(App.appliction);
    }
}
