package com.itheima.rbclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.HomeCategory;
import com.itheima.rbclient.ui.fragment.HomeFragment;

import java.util.List;

/**
 * Created by yt on 2016/8/2.
 */
public class HomeListAdapter extends BaseAdapter {

    private List<HomeCategory> categroy;
    HomeFragment homeFragment;
    public HomeListAdapter(HomeFragment homeFragment, List<HomeCategory> categroy) {
        this.categroy=categroy;
        this.homeFragment = homeFragment;
    }

    @Override
    public int getCount() {
        return categroy.size();
    }


    @Override
    public Object getItem(int position) {
        return categroy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        HomeCategory item = categroy.get(position);
        if (convertView == null)
            view = View.inflate(homeFragment.getActivity(),R.layout.home_item, null);

        else
            view = convertView;
        ((TextView) view.findViewById(R.id.textContent)).setText(item.getTitle());
        ((ImageView)view.findViewById(R.id.imgIcon)).setBackgroundResource(item.getImgresid());
        return view;
    }

}
