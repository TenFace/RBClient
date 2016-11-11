package com.itheima.rbclient.protocol;

import com.android.volley.Request;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.TopicResponse;
import com.itheima.rbclient.util.NetUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.protocol.IProtocol;

/**
 * Created by xiongmc on 2016/6/7.
 */
public class TopicProtocol implements IProtocol {
    @Override
    public Request doRequest(HttpLoader loader, HttpLoader.HttpListener listener) {

        //----促销快报-------------
        /*HttpParams params = new HttpParams().put("page", "1").put("pageNum", "8");
        return loader.get(RBConstants.URL_TOPIC, params, TopicResponse.class,
                RBConstants.REQUEST_CODE_TOPIC, listener);*/

        //----购物车-------------
        /*HttpParams params = new HttpParams().put("sku", "1:3:1,2,3,4|2:2:2,3");
        return loader.post(RBConstants.URL_CART, params,TopicResponse.class,
                RBConstants.REQUEST_CODE_CART, listener);*/

        //----结算中心-------------
        /*HttpParams params = new HttpParams().put("sku", "1:3:1,2,3,4|2:2:2,3");
        params.addHeader(NetUtil.generateHeaders(App.appliction));
        return loader.post(RBConstants.URL_CHECKOUT, params, TopicResponse.class, RBConstants.REQUEST_CODE_CHECKOUT, listener);
        */

        //----地址列表-------------
        HttpParams params = new HttpParams();
        params.addHeader(NetUtil.generateHeaders(App.appliction));
        return loader.post(RBConstants.URL_ADDRESS_LIST, params, TopicResponse.class, RBConstants.REQUEST_CODE_ADDRESS_LIST, listener);
    }
}
