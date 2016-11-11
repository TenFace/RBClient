package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by lenovo on 2016/8/3.
 */
public class Help implements IResponse {

    /**
     * helpList : [{"id":1,"title":"购物指南"},{"id":2,"title":"配送方式"},{"id":3,"title":"售后服务"},{"id":4,"title":"产品更新"}]
     * response : help
     */

    private String response;
    /**
     * id : 1
     * title : 购物指南
     */

    private List<HelpListBean> helpList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<HelpListBean> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpListBean> helpList) {
        this.helpList = helpList;
    }

    public static class HelpListBean {
        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
