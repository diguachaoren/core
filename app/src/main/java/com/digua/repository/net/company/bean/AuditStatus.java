package com.digua.repository.net.company.bean;

import java.io.Serializable;

/**
 * Created by lmq.
 * Date: 2022/9/8
 */
public class AuditStatus implements Serializable {

    private String title;
    private String clsName;
    private String name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
