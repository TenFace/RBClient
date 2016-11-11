package com.itheima.rbclient.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.HotproductAppInfo;

/**
 * Created by yt on 2016/8/7.
 */
public class HotproductHolder extends BaseHolder {

    private View view;
    private ImageView imageView;
    private TextView tittle;
    private TextView ysale;
    private TextView sale;
    private HotproductAppInfo.ProductListBean appInfo;

    @Override
    public View initHolderView() {
        view = View.inflate(App.appliction.getApplicationContext(), R.layout.item_hotproduct,null);
        imageView = (ImageView) view.findViewById(R.id.iv_item_hotproduct);
        tittle = (TextView) view.findViewById(R.id.tv_item_hotproduct_name);
        ysale = (TextView) view.findViewById(R.id.tv_item_hotproduct_ysale);
        sale = (TextView) view.findViewById(R.id.tv_item_hotproduct_sale);
        return view;
    }

    @Override
    public void bindData(Object data) {
        appInfo = (HotproductAppInfo.ProductListBean) data;
        String imgurl= RBConstants.URL_SERVEhome+ appInfo.getPic();
        App.IL.get(imgurl, ImageLoader.getImageListener(imageView, R.drawable.image1, R.drawable.image1));
        tittle.setText(appInfo.getName());
        ysale.setText(("原价格"+ appInfo.getMarketPrice()));
        sale.setText((""+ appInfo.getPrice()));

    }
}
