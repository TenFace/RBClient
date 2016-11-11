package com.itheima.rbclient.bean;

/**
 * 首页栏目
 *
 * @author liu
 */
public class HomeCategory {
    private int imgresid;
    private String title;

    public HomeCategory() {
    }

    public HomeCategory(int imgresid, String title) {
        super();
        this.imgresid = imgresid;
        this.title = title;
    }

    public int getImgresid() {
        return imgresid;
    }

    public void setImgresid(int imgresid) {
        this.imgresid = imgresid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
