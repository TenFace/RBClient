package com.itheima.rbclient.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.SearchList;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：pyf on 2016/8/4 15:25
 * <p/>
 * 邮箱：1173408486@qq.com
 */

public class SearchResultAdapter extends BaseAdapter {
    public SearchResultAdapter(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<SearchList.ProductListBean> arrayList = new ArrayList();

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(App.appliction, R.layout.item_favorite, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvFavoriteItemPortfolio.setText("");
        SearchList.ProductListBean productListBean = arrayList.get(position);
        holder.tvFavoriteItemName.setText(productListBean.getName());
        holder.tvFavoriteItemSale.setText("￥"+productListBean.getPrice());
        holder.tvFavoriteItemOriginal.setText("￥"+productListBean.getMarketPrice());
        holder.tvFavoriteItemOriginal.getPaint().setColor(Color.BLACK);
        holder.tvFavoriteItemOriginal.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
      /*  final ViewHolder finalHolder = holder;
        App.IL.get(RBConstants.URL_SERVER + productListBean.getPic(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                finalHolder.ivFavoriteItem.set;
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });*/

        App.HL.display(holder.ivFavoriteItem, RBConstants.URL_SERVER + productListBean.getPic());
       /* holder.ivFavoriteItem.setBackgroundResource(App.IL.get(RB));
        holder.tvFavoriteItemEvaluate.setText(productListBean.);*/

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_favorite_item)
        ImageView ivFavoriteItem;
        @InjectView(R.id.tv_favorite_item_name)
        TextView tvFavoriteItemName;
        @InjectView(R.id.tv_favorite_item_portfolio)
        TextView tvFavoriteItemPortfolio;
        @InjectView(R.id.tv_favorite_item_sale)
        TextView tvFavoriteItemSale;
        @InjectView(R.id.tv_favorite_item_original)
        TextView tvFavoriteItemOriginal;
        @InjectView(R.id.tv_favorite_item_evaluate)
        TextView tvFavoriteItemEvaluate;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
