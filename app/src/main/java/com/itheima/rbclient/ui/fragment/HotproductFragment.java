package com.itheima.rbclient.ui.fragment;


import com.android.volley.VolleyError;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.adapter.HotproductAdapter;
import com.itheima.rbclient.bean.HotproductAppInfo;
import com.itheima.rbclient.eventBean.EventId;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;

/**
 * Created by yt on 2016/8/7.
 */
public class HotproductFragment extends BaceFragment {

    @Override
    void SonRefreshView() {
        SonTittle = "热卖单品";
    }

    @Override
    void requestNetData() {
        String url = RBConstants.URL_HOTPRODUCT;
        Class<? extends IResponse> clazz;
        clazz = HotproductAppInfo.class;
         SonrequestCode = RBConstants.REQUEST_CODE_HOTPRODUCT;
        App.HL.get(url, params, clazz, SonrequestCode, this,false);
    }

    @Override
    protected void Sononitemclick(int position) {
        HotproductAppInfo.ProductListBean appinfo= (HotproductAppInfo.ProductListBean) list.get(position);
        EventBus.getDefault().postSticky(new EventId(appinfo.getId()));
        swichToChildFragment(new MarketDetailFragment(), true);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == SonrequestCode) {
            oldlist = (( HotproductAppInfo) response).getProductList();
            adapter = new HotproductAdapter((ArrayList) list);
            requestseccseful();

        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}