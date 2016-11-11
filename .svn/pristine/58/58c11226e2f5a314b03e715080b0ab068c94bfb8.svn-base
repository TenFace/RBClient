package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by lenovo on 2016/8/3.
 */
public class UserInfo implements IResponse {

    public int error_code;
    public String error;

    /**
     * response : userInfo
     * userInfo : {"bonus":0,"level":"普通会员","userId":30505,"orderCount":"20","favoritesCount":"12"}
     */

    private String response;
    /**
     * bonus : 0
     * level : 普通会员
     * userId : 30505
     * orderCount : 20
     * favoritesCount : 12
     */

    private UserInfoBean userInfo;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        private int bonus;
        private String level;
        private int userId;
        private String orderCount;
        private String favoritesCount;

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
        }

        public String getFavoritesCount() {
            return favoritesCount;
        }

        public void setFavoritesCount(String favoritesCount) {
            this.favoritesCount = favoritesCount;
        }
    }
}
