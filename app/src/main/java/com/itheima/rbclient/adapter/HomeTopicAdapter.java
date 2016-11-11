package com.itheima.rbclient.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.HomeTopicAppInfo;

import java.util.List;

/**
 * Created by yt on 2016/8/2.
 */
public class HomeTopicAdapter extends PagerAdapter {
    List<HomeTopicAppInfo.HomeTopicBean> list;
    private View view;

    public HomeTopicAdapter(List<HomeTopicAppInfo.HomeTopicBean> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return (list.size())*10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//		super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            HomeTopicAppInfo.HomeTopicBean homeTopicBean = list.get(position%(list.size()));
            String imgurl =RBConstants.URL_SERVEhome +  homeTopicBean.getPic();
            view = View.inflate(App.appliction, R.layout.home_item_viewpager , null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_home_itemviewpager);
            App.IL.get(imgurl, ImageLoader.getImageListener(imageView, R.drawable.image1, R.drawable.image1));
            container.addView(view);


        return view;
    }


}
