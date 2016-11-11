package com.itheima.rbclient;

/**
 * 常量类
 * 用来保存一些几乎从不轻易改动的变量
 *
 * Created by xiongmc on 2016/4/28.
 */
public class RBConstants {

    /**使用sp存储userid,的键名*/
    public static final String USERID = "userid";
    public static final String USERNAME = "username";

//    public static final String URL_SERVER = "http://192.168.11.71:8080/RedBabyServer/";
    public static final String URL_SERVEhome = "http://192.168.78.45:8080/RedBabyServer";
    public static final String URL_SERVER = "http://192.168.78.34:8080/RedBabyServer/";

    /**
     * 促销快报
     *
     */
    public static final String URL_TOPIC = URL_SERVER + "topic";
    public static final int REQUEST_CODE_TOPIC = 1;

    /**
     * 购物车
     *
     */
    public static final String URL_CART = URL_SERVER + "cart";
    public static final int REQUEST_CODE_CART = 2;

    /**
     * 结算中心
     *
     */
    public static final String URL_CHECKOUT = URL_SERVER + "checkout";
    public static final int REQUEST_CODE_CHECKOUT = 3;

    /**
     * 登录
     *
     */
    public static final String URL_LOGIN = URL_SERVER + "login";
    public static final int REQUEST_CODE_LOGIN = 4;

    /**
     * 地址
     *
     */
    public static final String URL_ADDRESS_LIST = URL_SERVER + "addresslist";
    public static final int REQUEST_CODE_ADDRESS_LIST = 5;

    /**
     * 发票
     *
     */
    public static final String URL_INVOICE = URL_SERVER + "invoice";
    public static final int REQUEST_CODE_INVOICE = 22;

    /**
     * 删除地址
     *
     */
    public static final String URL_ADDRESS_DELETE = URL_SERVER + "addressdelete";
    public static final int REQUEST_CODE_ADDRESS_DELETE = 21;

    /**
     * 注册
     *
     */
    public static final String URL_REGISTER = URL_SERVER + "register";
    public static final int REQUEST_CODE_REGISTER = 6;
    /**
     * 首页轮播图
     *
     * */
    public static final String URL_Home_topic = URL_SERVER + "home";
    public static final int REQUEST_CODE_HomeTopic = 7;

    /**
     * 收藏
     *
     */
    public static final String URL_FAVORITE = URL_SERVER + "favorites";
    public static final int REQUEST_CODE_FAVORITE = 8;

    /**
     * 用户信息
     *
     */
    public static final String URL_USERINFO = URL_SERVER + "userinfo";
    public static final int REQUEST_CODE_USERINFO = 9;

    /**
     * 帮助
     *
     */
    public static final String URL_HELP = URL_SERVER + "help";
    public static final int REQUEST_CODE_HELP = 10;

    /**
     * 帮助详情
     *
     */
    public static final String URL_HELP_DETAIL = URL_SERVER + "helpDetail";
    public static final int REQUEST_CODE_HELP_DETAIL = 11;

    /**
     * 三级地址详情
     *
     */
    public static final String URL_ADDRESS_AREA = URL_SERVER + "addressarea";
    public static final int REQUEST_CODE_ADDRESS_AREA = 13;




    /**
     * 搜索
     *
     */
    public static final String URL_SEARCH = URL_SERVER + "search/recommend";
    public static final int REQUEST_CODE_SEARCH = 60;

    /**
     * 热卖单品
     *
     */
    public static final String URL_HOTPRODUCT= URL_SERVER + "hotproduct";
    public static final int REQUEST_CODE_HOTPRODUCT = 103;
   /**
    * 订单详情
    *
    */

   public static final String URL_ORDERDETAIL = URL_SERVER + "orderdetail";
   public static final int REQUEST_CODE_ORDERDETAIL = 15;

   /**
     * 限时抢购
    *
     */
    public static final String URL_LIMITBUY= URL_SERVER + "limitbuy";
    public static final int REQUEST_CODE_LIMITBUY = 104;
    /**
     * 新品上架
     *
     */
    public static final String URL_NEWPRODUCT= URL_SERVER + "newproduct";
    public static final int REQUEST_CODE_NEWPRODUCT = 102;

    /**
     * 商品详情页面的访问服务器的常量
     *
     */
    public static final String URL_MarketDetail = URL_SERVER + "product";
    public static final int REQUEST_CODE_MarketDetail = 70;//这是请求成功后的返回码

    /**
     * 保存地址
     *
     */
    public static final String URL_SAVE_ADDRESS = URL_SERVER + "addresssave";
    public static final int REQUEST_CODE_SAVE_ADDRESS = 14;
    /**
     * 订单列表
     *
     */
    public static final String URL_ORDERLIST = URL_SERVER + "orderlist";
    public static final int REQUEST_CODE_ORDERLIST = 12;

    /**
     * 搜索商品列表
     * 
     */
    public static final String URL_SEARCH_LIST = URL_SERVER + "search";
    public static final int REQUEST_CODE_SEARCH_LIST = 61;
}
