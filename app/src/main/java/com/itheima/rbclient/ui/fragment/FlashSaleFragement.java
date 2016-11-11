package com.itheima.rbclient.ui.fragment;

import android.view.View;

import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.FlashSaleAdapter;
import com.itheima.rbclient.bean.FlashSaleAppInfo;
import com.itheima.rbclient.eventBean.EventId;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;

/**
 * Created by yt on 2016/8/6.
 */
public class FlashSaleFragement extends BaceFragment{

    private int page=0;
    private int pageNum=10;

    @Override
    void SonRefreshView() {
        SonTittle ="限时抢购";
        radioGroup.setVisibility(View.GONE);
    }

    protected void requestNetData() {
        String url = RBConstants.URL_LIMITBUY;
        HttpParams params = new HttpParams();
        params.put("page",""+page);
        params.put("pageNum",""+pageNum);
        Class<? extends IResponse> clazz;
        clazz = FlashSaleAppInfo.class;
        int requestcode = RBConstants.REQUEST_CODE_LIMITBUY;
        App.HL.get(url, params, clazz, requestcode, this,false);

    }

    @Override
    protected void Sononitemclick(int position) {
        FlashSaleAppInfo.ProductListBean appinfo= (FlashSaleAppInfo.ProductListBean) list.get(position);
        EventBus.getDefault().postSticky(new EventId(appinfo.getId()));
        swichToChildFragment(new MarketDetailFragment(), true);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == RBConstants.REQUEST_CODE_LIMITBUY) {

            oldlist = ((FlashSaleAppInfo) response).getProductList();
            adapter = new FlashSaleAdapter((ArrayList) list);
            requestseccseful();
        }

    }
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

}
