package com.digua.core.utils.eventbus;

/**
 * Created by yujia
 * on 2018/5/29.
 */

public class Event<T> {
    private String tag;
    private T data;

    public Event(String tag) {
        this.tag = tag;
    }

    public Event(String tag, T data) {
        this.tag = tag;
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}