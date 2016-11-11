package com.itheima.rbclient.protocol;

import com.android.volley.Request;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.OrderDetailResponse;
import com.itheima.rbclient.util.NetUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by xuan on 2016/8/8.
 */
public class OrderDetailProtocol implements IResponse {

    private String orderid;

    public OrderDetailProtocol(String orderid){

        this.orderid = orderid;
    }

    public Request doRequest(HttpLoader loader, HttpLoader.HttpListener listener){
        HttpParams params = new HttpParams();
        params.addHeader(NetUtil.generateHeaders(App.appliction));
        params.put("orderId",orderid);
        return loader.post(RBConstants.URL_ORDERDETAIL,params, OrderDetailResponse.class,RBConstants.REQUEST_CODE_ORDERDETAIL,listener);

    }
}
