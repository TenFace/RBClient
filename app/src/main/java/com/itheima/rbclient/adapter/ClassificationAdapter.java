package com.itheima.rbclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.ClassificationResponse;

import org.senydevpkg.net.HttpLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/2.
 */
public class ClassificationAdapter extends BaseAdapter {
    private ArrayList<ClassificationResponse.CategoryBean> list = new ArrayList<>();
    public ClassificationAdapter(ArrayList<ClassificationResponse.CategoryBean> responses){
        this.list = responses;
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
        ViewHolder holder = null;
        if(convertView == null){
            convertView = View.inflate(App.appliction, R.layout.item_classification,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //由于服务器返回的图片有问题，所以把下面显示图片的代码注释了
        App.HL.display(holder.classification_item_icon, RBConstants.URL_SERVER+list.get(position).getPic());
        holder.classification_item_name.setText(list.get(position).getName());
        holder.classification_item_target.setText(list.get(position).getTag());
        return convertView;
    }
    class ViewHolder{
        public ImageView classification_item_icon;
        public ImageView classification_item_arrow;
        public TextView classification_item_name;
        public TextView classification_item_target;
        public ViewHolder(View view){
            classification_item_arrow = (ImageView) view.findViewById(R.id.classification_item_arrow);
            classification_item_icon = (ImageView) view.findViewById(R.id.classification_item_icon);
            classification_item_name = (TextView) view.findViewById(R.id.classification_item_name);
            classification_item_target = (TextView) view.findViewById(R.id.classification_item_target);
        }
    }
}
