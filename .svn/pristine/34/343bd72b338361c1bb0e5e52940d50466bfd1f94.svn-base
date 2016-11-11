package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;

/**
 * Created by xuan on 2016/8/5.
 */
public class OrderlistResponse implements IResponse {

    /**返回error的参数*/
    public int error_code;
    public String error;

    /*{
        "response": "orderList",
            "orderList":[
        {
            "orderId": "260092",                //订单ID
                "status": "未处理",                    //订单显示状态
                "time": "1439528260115",         //下单时间
                "price": "208",                    //订单金额
                "flag":"1"        //订单标识，1=>可删除可修改 2=>不可修改 3=>已完成
        }
        ]
    }*/
    public String response;
    public ArrayList<OrderInfo> orderList;

    public class OrderInfo {
        public String status;
        public String orderId;
        public String time;
        public String price;
        public String flag;
    }
}
