package com.itheima.rbclient.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.itheima.rbclient.App;
import com.itheima.rbclient.R;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.TopicResponse;

import org.senydevpkg.base.BaseHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiongmc on 2016/6/7.
 */
public class TopicHolder extends BaseHolder<TopicResponse.TopicBean> {
    @InjectView(R.id.iv)
    ImageView mIv;
    @InjectView(R.id.tv)
    TextView mTv;

    public TopicHolder(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(App.appliction, R.layout.item_brand, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void bindData(TopicResponse.TopicBean data) {
        mTv.setText(data.name);
        App.IL.get(RBConstants.URL_SERVER + data.pic,
                ImageLoader.getImageListener(mIv, R.mipmap.ic_launcher, R.mipmap.ic_launcher));

//        App.HL.display(mIv, RBConstants.URL_SERVER + data.pic);
    }
}
