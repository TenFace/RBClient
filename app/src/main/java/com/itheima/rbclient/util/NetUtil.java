package com.itheima.rbclient.util;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiongmc on 2016/6/9.
 */
public class NetUtil {

    /**
     * 生成公共的请求头部，一般一个应用的所有接口的请求头都是固定的。
     * @param context
     * @return
     */
    public static Map<String, String> generateHeaders(Context context) {
        Map<String, String> headers = new HashMap<>();
        /**
         * Notice：从SP中去取userid，userid在登录后由服务器返回。
         * eg:
         *  SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
         *  header.put("userid", sp.getString("userid", ""))
         */
//        headers.put("userid", "154636");
        headers.put("userid", "20428");

        //        appkey        软件身份key
        //        udid          手机客户端的唯一标识
        //        os            操作系统名称
        //        osversion     操作系统版本
        //        appversion    APP版本
        //        sourceid      推广ID
        //        ver           通讯协议版本
        //        userid        用户ID
        //        usersession   登陆后得到的用户唯一性标识
        //        unique        激活后得到的设备唯一性标识
        return headers;
    }
}
