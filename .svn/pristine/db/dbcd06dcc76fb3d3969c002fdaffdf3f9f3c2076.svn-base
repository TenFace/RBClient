package com.itheima.rbclient.ui.fragment;

import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.PromotionAdap;
import com.itheima.rbclient.bean.salesPromotionAppInfo;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yt on 2016/8/4.
 */
public class ProjectListFragment extends BaseListFragment implements HttpLoader.HttpListener{


    private HashMap<String ,String> hashMap;
    private int page=0;
    private int pageNum=10;


    @Override
    protected HashMap<String, String> sonHashmap() {
        hashMap = new HashMap<String ,String>();
        hashMap.put("tv_home_salespromotion","促销快报");
        return hashMap;
    }

    protected void requestNetData() {
        String url = RBConstants.URL_TOPIC;
        HttpParams params = new HttpParams();
        params.put("page",""+page);
        params.put("pageNum",""+pageNum);
        Class<? extends IResponse> clazz;
        clazz = salesPromotionAppInfo.class;
        int requestcode = RBConstants.REQUEST_CODE_TOPIC;
        App.HL.get(url, params, clazz, requestcode, this);

    }

    @Override
    protected PromotionAdap getAdapter() {
        return new PromotionAdap((ArrayList) list);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {

    }
}
