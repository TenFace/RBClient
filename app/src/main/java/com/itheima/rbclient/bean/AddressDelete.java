package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by lenovo on 2016/8/7.
 */
public class AddressDelete implements IResponse {

    /**
     * response : addressDelete
     */

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
