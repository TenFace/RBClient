package com.itheima.rbclient.holder;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.Favorite;

import org.senydevpkg.base.BaseHolder;
import org.senydevpkg.net.HttpLoader;

/**
 * Created by lenovo on 2016/8/3.
 */
public class FavoriteHolder extends BaseHolder<Favorite.ProductListBean> {

    private ImageView ivFavoriteItem;
    private TextView tvFavoriteItemName;
    private TextView tvFavoriteItemPortfolio;
    private TextView tvFavoriteItemSale;
    private TextView tvFavoriteItemOriginal;
    private TextView tvFavoriteItemEvaluate;

    public FavoriteHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.item_favorite, null);
        ivFavoriteItem = (ImageView) view.findViewById(R.id.iv_favorite_item);
        tvFavoriteItemName = (TextView) view.findViewById(R.id.tv_favorite_item_name);
        tvFavoriteItemPortfolio = (TextView) view.findViewById(R.id.tv_favorite_item_portfolio);
        tvFavoriteItemSale = (TextView) view.findViewById(R.id.tv_favorite_item_sale);
        tvFavoriteItemOriginal = (TextView) view.findViewById(R.id.tv_favorite_item_original);
        tvFavoriteItemEvaluate = (TextView) view.findViewById(R.id.tv_favorite_item_evaluate);
        return view;
    }

    @Override
    public void bindData(Favorite.ProductListBean data) {
        HttpLoader.getInstance(getContext()).display(ivFavoriteItem, RBConstants.URL_SERVER + data.getPic());
        tvFavoriteItemName.setText(data.getName());
        //tvFavoriteItemPortfolio.setVisibility(View.INVISIBLE);
        tvFavoriteItemSale.setText(data.getPrice());
        tvFavoriteItemOriginal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvFavoriteItemOriginal.setText(data.getMarketPrice());
    }
}
