package com.itheima.rbclient.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.bean.OrderlistResponse;
import com.itheima.rbclient.widget.OrderRefreshListView;

import java.util.ArrayList;

/**
 * Created by xuan on 2016/8/4.
 */
public class OrderFormViewPagerAdapter extends BasePagerAdapter {

    private Context context;

    public OrderFormViewPagerAdapter(Context context, ArrayList list) {
        super(list);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
            Log.d("dddd", "我进来了"+position);
        if (list != null && list.size() != 0) {//说明有订单信息
            OrderRefreshListView listView = (OrderRefreshListView) View.inflate(context, R.layout.listview_order, null);
            listView.setAdapter(new OrderListViewAdapter());
            view = listView;
            container.addView(listView);
        }else {//没有订单信息
            TextView textView = (TextView) View.inflate(context,R.layout.textview_order,null);
            view = textView;
            container.addView(textView);
        }
            return view;
    }

    private class OrderListViewAdapter extends BaseAdapter {
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
            HolderView holderView = null;
            if (convertView == null) {//没有缓存对象
                holderView = new HolderView();
                convertView = View.inflate(context, R.layout.item_listview_order, null);
                holderView.tv_order_id = (TextView) convertView.findViewById(R.id.tv_order_id);
                holderView.tv_order_price = (TextView) convertView.findViewById(R.id.tv_order_price);
                holderView.tv_order_state = (TextView) convertView.findViewById(R.id.tv_order_state);
                holderView.tv_order_time = (TextView) convertView.findViewById(R.id.tv_order_time);

                convertView.setTag(holderView);
            }else {//有缓存对象
                holderView = (HolderView) convertView.getTag();
            }

            OrderlistResponse.OrderInfo info = (OrderlistResponse.OrderInfo) list.get(position);
            holderView.tv_order_id.setText(info.orderId);
            holderView.tv_order_price.setText(info.price);
            holderView.tv_order_state.setText(info.status);
            holderView.tv_order_time.setText(info.time);

            return convertView;
        }
    }

    static class HolderView{
        //订单id
        TextView tv_order_id;
        //订单价格
        TextView tv_order_price;
        //订单状态
        TextView tv_order_state;
        //订单生成的时间
        TextView tv_order_time;

    }
}
