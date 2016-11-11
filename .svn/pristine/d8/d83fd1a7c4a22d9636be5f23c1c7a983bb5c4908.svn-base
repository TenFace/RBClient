package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class CartResponse implements IResponse {

    /**
     * cart : [{"prodNum":3,"product":{"buyLimit":10,"id":1,"name":"韩版时尚蕾丝裙","number":"100","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":4,"k":"尺码","v":"XXL"}]}},{"prodNum":2,"product":{"buyLimit":10,"id":2,"name":"粉色毛衣","number":"13","pic":"/images/product/detail/q1.jpg","price":100,"productProperty":[{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"}]}}]
     * prom : ["促销信息一","促销信息二"]
     * response : cart
     * totalCount : 5
     * totalPoint : 100
     * totalPrice : 1250
     */

    private String response;
    private int totalCount;
    private int totalPoint;
    private int totalPrice;
    /**
     * prodNum : 3
     * product : {"buyLimit":10,"id":1,"name":"韩版时尚蕾丝裙","number":"100","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":4,"k":"尺码","v":"XXL"}]}
     */

    private List<CartEntity> cart;
    private List<String> prom;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCart(List<CartEntity> cart) {
        this.cart = cart;
    }

    public void setProm(List<String> prom) {
        this.prom = prom;
    }

    public String getResponse() {
        return response;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<CartEntity> getCart() {
        return cart;
    }

    public List<String> getProm() {
        return prom;
    }

    public static class CartEntity {
        private int prodNum;
        /**
         * buyLimit : 10
         * id : 1
         * name : 韩版时尚蕾丝裙
         * number : 100
         * pic : /images/product/detail/c3.jpg
         * price : 350
         * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":4,"k":"尺码","v":"XXL"}]
         */

        private ProductEntity product;

        public void setProdNum(int prodNum) {
            this.prodNum = prodNum;
        }

        public void setProduct(ProductEntity product) {
            this.product = product;
        }

        public int getProdNum() {
            return prodNum;
        }

        public ProductEntity getProduct() {
            return product;
        }

        public static class ProductEntity {
            private int buyLimit;
            private int id;
            private String name;
            private String number;
            private String pic;
            private int price;
            /**
             * id : 1
             * k : 颜色
             * v : 红色
             */

            private List<ProductPropertyEntity> productProperty;

            public void setBuyLimit(int buyLimit) {
                this.buyLimit = buyLimit;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public void setProductProperty(List<ProductPropertyEntity> productProperty) {
                this.productProperty = productProperty;
            }

            public int getBuyLimit() {
                return buyLimit;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getNumber() {
                return number;
            }

            public String getPic() {
                return pic;
            }

            public int getPrice() {
                return price;
            }

            public List<ProductPropertyEntity> getProductProperty() {
                return productProperty;
            }

            public static class ProductPropertyEntity {
                private int id;
                private String k;
                private String v;

                public void setId(int id) {
                    this.id = id;
                }

                public void setK(String k) {
                    this.k = k;
                }

                public void setV(String v) {
                    this.v = v;
                }

                public int getId() {
                    return id;
                }

                public String getK() {
                    return k;
                }

                public String getV() {
                    return v;
                }
            }
        }
    }
}
