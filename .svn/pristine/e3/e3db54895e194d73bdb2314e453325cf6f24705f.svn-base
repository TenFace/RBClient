package com.itheima.rbclient.protocol;

import com.android.volley.Request;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.CartResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.protocol.IProtocol;

/**
 * Created by Administrator on 2016/8/3.
 */
public class RequestProtocol implements IProtocol {
    @Override
    public Request doRequest(HttpLoader loader, HttpLoader.HttpListener listener) {
        HttpParams params = new HttpParams().put("sku", "1:3:1,4|2:2:2,3");
        return loader.post(RBConstants.URL_CART, params, CartResponse.class,
                RBConstants.REQUEST_CODE_CART, listener);
    }
}
