package com.itheima.rbclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.MyProductList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class ProductListAdapter extends BaseAdapter  {
    private List<MyProductList.ProductListBean> productList;
    public ProductListAdapter(List<MyProductList.ProductListBean> productList){
        this.productList = productList;
    }
    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = View.inflate(App.appliction, R.layout.item_productlist_gridview,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        App.HL.display(holder.imageView, RBConstants.URL_SERVER+productList.get(position).getPic());
        holder.name.setText(productList.get(position).getName());
        holder.price.setText(productList.get(position).getPrice());

        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView price;
        public ViewHolder(View convertView){
            ImageView imageView = (ImageView) convertView.findViewById(R.id.item_gridview_iv);
            TextView name = (TextView) convertView.findViewById(R.id.item_gridview_name);
            TextView price = (TextView) convertView.findViewById(R.id.item_gridview_price);
        }
    }
}
