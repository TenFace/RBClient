package com.itheima.rbclient.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.FlashSaleAppInfo;

/**
 * Created by yt on 2016/8/6.
 */
public class FlashSaleHolder extends BaseHolder {

    private ImageView imageView;
    private TextView title;
    private TextView ysale;
    private TextView sale;
    private TextView time;
    private View view;

    @Override
    public View initHolderView() {
        view = View.inflate(App.appliction.getApplicationContext(), R.layout.item_flashsalexml,null);
        imageView = (ImageView) view.findViewById(R.id.iv_item_flashpic);
        title = (TextView) view.findViewById(R.id.tv_item_flash_name);
        ysale = (TextView) view.findViewById(R.id.tv_item_flash_ydale);
        sale = (TextView) view.findViewById(R.id.tv_item_flash_sale);
        time = (TextView) view.findViewById(R.id.tv_item_flash_time);

        return view;
    }

    @Override
    public void bindData(Object data) {
        FlashSaleAppInfo.ProductListBean appInfo  = (FlashSaleAppInfo.ProductListBean) data;
        String imgurl= RBConstants.URL_SERVEhome+appInfo.getPic();
        App.IL.get(imgurl, ImageLoader.getImageListener(imageView, R.drawable.image1, R.drawable.image1));
        title.setText(appInfo.getName());
        ysale.setText(("原价格"+appInfo.getPrice()));
        sale.setText((""+appInfo.getLimitPrice()));
        time.setText(("剩余时间12:45:30"));
    }
}
