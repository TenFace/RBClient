package com.itheima.rbclient.bean;

import org.senydevpkg.net.resp.IResponse;

import java.util.List;

/**
 * Created by lenovo on 2016/8/8.
 */
public class Invoice implements IResponse {

    /**
     * response : invoice
     * invoice : [{"id":"1","content":"图书"},{"id":"2","content":"服装"}]
     */

    private String response;
    /**
     * id : 1
     * content : 图书
     */

    private List<InvoiceBean> invoice;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<InvoiceBean> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<InvoiceBean> invoice) {
        this.invoice = invoice;
    }

    public static class InvoiceBean {
        private String id;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
