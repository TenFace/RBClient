package com.itheima.rbclient.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.salesPromotionAppInfo;
import com.itheima.rbclient.util.LogUtil;

/**
 * Created by yt on 2016/8/4.
 */
public class PromotionHolder extends BaseHolder {

    private View view;
    private TextView textView;
    private ImageView imageView;
    private salesPromotionAppInfo.TopicBean appInfo;

    @Override
    public View initHolderView() {
        view = View.inflate(App.appliction.getApplicationContext(), R.layout.item_home_salespromotion,null);
        textView = (TextView) view.findViewById(R.id.tv_item_home_salespromotion);
        imageView = (ImageView) view.findViewById(R.id.iv_item_home_salespromotion);
        return view;
    }

    @Override
    public void bindData(Object data) {


        appInfo = (salesPromotionAppInfo.TopicBean) data;
        LogUtil.d(this,appInfo.getPic());
        String imgurl= RBConstants.URL_SERVEhome+appInfo.getPic();
        textView.setText(appInfo.getName());
        App.IL.get(imgurl, ImageLoader.getImageListener(imageView, R.drawable.image1, R.drawable.image1));

    }
}
