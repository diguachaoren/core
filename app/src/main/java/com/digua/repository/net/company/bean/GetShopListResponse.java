package com.digua.repository.net.company.bean;

import java.io.Serializable;
import java.util.List;

public class GetShopListResponse implements Serializable {


    private Integer code;
    private String message;
    private List<ShopBean> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ShopBean> getData() {
        return data;
    }

    public void setData(List<ShopBean> data) {
        this.data = data;
    }

}
