package com.itheima.rbclient.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.ProductDetail;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by 赵志杰 on 2016/8/3.
 * 这是商品详情大图ViewPager的adapter
 */
public class BigMarketDetailAdapter extends PagerAdapter {
    public List<String> bigList;
    //构造方法需要一个Product的data数据。
    ProductDetail.ProductBean data;

    public BigMarketDetailAdapter(ProductDetail.ProductBean data) {
        this.data = data;
        bigList = data.getBigPic();//得到大图的集合
    }



    //这是大图片的holder
    //BigMarketDetailHolder bigMarketDetailHolder;

    @Override
    public int getCount() {
        //首先需要得到一个Product的大图的图片的个数
        return bigList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //显示大图
        PhotoView photoView = new PhotoView(App.appliction);
        App.IL.get(RBConstants.URL_SERVER + bigList.get(position),
                ImageLoader.getImageListener(photoView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        container.addView(photoView);
        return photoView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
