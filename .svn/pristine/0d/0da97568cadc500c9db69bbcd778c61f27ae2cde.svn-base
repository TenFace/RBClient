package com.itheima.rbclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：pyf on 2016/8/3 17:41
 * <p>
 * 邮箱：1173408486@qq.com
 */

public class SearchAdapter extends BaseAdapter {
    private List<String> list;

    public SearchAdapter(List list) {
        this.list = list;
    }

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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(App.appliction, R.layout.text_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvList.setText(list.get(position));

        return convertView;
    }

    public void setSearchKeywords(ArrayList<String> list) {
        this.list = list;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_list)
        TextView tvList;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
