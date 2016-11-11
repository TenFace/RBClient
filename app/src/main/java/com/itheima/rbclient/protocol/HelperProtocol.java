package com.itheima.rbclient.protocol;

import com.android.volley.Request;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.Help;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.protocol.IProtocol;

/**
 * Created by lenovo on 2016/8/3.
 */
public class HelperProtocol implements IProtocol {
    @Override
    public Request doRequest(HttpLoader loader, HttpLoader.HttpListener listener) {
        HttpParams params = new HttpParams();
        return loader.post(RBConstants.URL_HELP, params, Help.class, RBConstants.REQUEST_CODE_HELP, listener);
    }
}
