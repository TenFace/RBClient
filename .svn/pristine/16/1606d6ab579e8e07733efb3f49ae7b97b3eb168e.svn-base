package com.itheima.rbclient.protocol;

import com.android.volley.Request;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.UserInfo;
import com.itheima.rbclient.util.NetUtil;
import com.itheima.rbclient.util.PrefUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.protocol.IProtocol;

import java.util.Map;

/**
 * Created by lenovo on 2016/8/3.
 */
public class UserInfoProtocol implements IProtocol {
    @Override
    public Request doRequest(HttpLoader loader, HttpLoader.HttpListener listener) {
        HttpParams params = new HttpParams();
        Map<String, String> map = NetUtil.generateHeaders(App.appliction);
        //map.put(RBConstants.USERID, "" + PrefUtils.getInt(App.appliction, RBConstants.USERID, 0));
        params.addHeader(map);
        return loader.post(RBConstants.URL_USERINFO, params, UserInfo.class, RBConstants.REQUEST_CODE_USERINFO, listener);
    }
}
