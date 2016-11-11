package com.itheima.rbclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.rbclient.App;

import java.util.ArrayList;

/**
 * 作者：pyf on 2016/8/5 17:09
 * <p/>
 * 邮箱：1173408486@qq.com
 */

public class SearchHistoryAdapter extends BaseAdapter {
    public SearchHistoryAdapter(ArrayList<String> list) {
        this.list = list;
    }

    ArrayList<String> list = new ArrayList<>();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(App.appliction);
        view.setText(list.get(position));
        view.setTextSize(14);
        return view;
    }
}
