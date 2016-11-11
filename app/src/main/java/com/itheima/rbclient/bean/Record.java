package com.itheima.rbclient.bean;

import java.util.ArrayList;
import java.util.List;

public class Record {

    /** 声明并创建一个购物车对象，并使其私有化 */
    private static Record ourInstance = new Record();

    /** 对外提供公共的访问方式 */
    public static Record getInstance() {
        return ourInstance;
    }

    /** 私有构造方法，并在构造方法中创建商品集合的对象 */
    private Record() {
        productBeans = new ArrayList<>();
    }

    /** 这是购物车商品的集合 */
    List<ProductDetail.ProductBean> productBeans;

    public List<ProductDetail.ProductBean> getProductBeans() {
        return productBeans;
    }

    /** 添加商品到购物车的方法 */
    public void addRecord(ProductDetail.ProductBean productBean) {
        if (!productBeans.contains(productBean)) {
            productBeans.add(productBean);
        }
    }

}
