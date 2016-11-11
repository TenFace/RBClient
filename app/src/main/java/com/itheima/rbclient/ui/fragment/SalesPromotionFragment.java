package com.itheima.rbclient.ui.fragment;

import android.view.View;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.SalesPromotionAdapter;
import com.itheima.rbclient.bean.salesPromotionAppInfo;
import com.itheima.rbclient.eventBean.EventId;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yt on 2016/8/4.
 */
public class SalesPromotionFragment extends BaceFragment{

    private HashMap<String ,String> hashMap;
    private int page=0;
    private int pageNum=10;



    @Override
    void SonRefreshView() {
        SonTittle = "促销快报";
        radioGroup.setVisibility(View.GONE);
    }

    protected void requestNetData() {
        String url = RBConstants.URL_TOPIC;
        HttpParams param = new HttpParams();
        param.put("page",""+page);
        param.put("pageNum",""+pageNum);
        Class<? extends IResponse> clazz;
        clazz = salesPromotionAppInfo.class;
        int requestcode = RBConstants.REQUEST_CODE_TOPIC;
        App.HL.get(url, param, clazz, requestcode, this,false);

    }

    @Override
    protected void Sononitemclick(int position) {
        salesPromotionAppInfo.TopicBean appinfo= (salesPromotionAppInfo.TopicBean) list.get(position);
        EventBus.getDefault().postSticky(new EventId(appinfo.getId()));
        swichToChildFragment(new MarketDetailFragment(), true);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if(requestCode==RBConstants.REQUEST_CODE_TOPIC){
            oldlist = ((salesPromotionAppInfo)response).getTopic();
//            LogUtil.d(this,list.get(0).getPic());
            adapter = new SalesPromotionAdapter((ArrayList) list);
            requestseccseful();

        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

}
