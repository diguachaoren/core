package com.digua.repository.net.company.bean;

import java.util.List;

public class GetUnsampledListResponse {

    private Integer code;
    private String message;
    private List<SampleItem> data;

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

    public List<SampleItem> getData() {
        return data;
    }

    public void setData(List<SampleItem> data) {
        this.data = data;
    }

}
