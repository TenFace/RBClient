package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.ProductDetail;

import org.senydevpkg.base.BaseHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 赵志杰 on 2016/8/3.
 * 这是商品详情--》大图显示的Holder(初始化BigMarketDetailFragment中每一个ViewPager的布局)
 */
public class BigMarketDetailHolder extends BaseHolder {

    @InjectView(R.id.iv_big_imag_item)
    ImageView iv_big_imag_item;
    @InjectView(R.id.tv_market_des)
    TextView tv_market_des;
    @InjectView(R.id.tv_market_price)
    TextView tv_market_price;
    public BigMarketDetailHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        //加载大图片每个条目的布局view
        View itemView = View.inflate(App.appliction, R.layout.fragment_big_imag_view_item,null);
        ButterKnife.inject(this,itemView);
        return itemView;
    }

    //绑定数据我们用到了第三方的PhotoView所以不再这里进行绑定数据了，而是在BigMarketDetailAdapter中绑定
    @Override
    public void bindData(Object data) {
        ProductDetail.ProductBean data1 = (ProductDetail.ProductBean) data;
        tv_market_des.setText(RBConstants.URL_SERVER+data1.getName());
        tv_market_price.setText(RBConstants.URL_SERVER+data1.getLimitPrice());
    }


}
