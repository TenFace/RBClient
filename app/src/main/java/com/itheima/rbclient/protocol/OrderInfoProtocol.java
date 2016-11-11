package com.itheima.rbclient.protocol;

import com.android.volley.Request;
import com.itheima.rbclient.App;
import com.itheima.rbclient.RBConstants;
import com.itheima.rbclient.bean.OrderlistResponse;
import com.itheima.rbclient.util.NetUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * 对于订单页面来说由于除了添加头信息外还有三个可变的参数,因此doRequest的参数
 * 也相比较其他接个相似的类有所改变
 * Created by xuan on 2016/8/5.
 */
public class OrderInfoProtocol implements IResponse{

    private String type;
    private String page;
    private String pageNum;

    public OrderInfoProtocol(String type, String page, String pageNum){

        this.type = type;//@param type  1/2/3 1=>近一个月订单(改为10分钟)  2=>一个月前订单(改为10分钟)  3=>已取消订单
        this.page = page;//@param page  第几页
        this.pageNum = pageNum;//@param pageNum  每页个数
    }

    /**
     *
     * @param loader
     * @param listener
     * @return
     */
    public Request doRequest(HttpLoader loader,HttpLoader.HttpListener listener){
        HttpParams params = new HttpParams();
        params.addHeader(NetUtil.generateHeaders(App.appliction));
        params.put("type",type);
        params.put("page",page);
        params.put("pageNum",pageNum);
        return loader.post(RBConstants.URL_ORDERLIST,params, OrderlistResponse.class,RBConstants.REQUEST_CODE_ORDERLIST,listener);
    }
}
