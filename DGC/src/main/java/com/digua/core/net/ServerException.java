package com.digua.core.net;

/**
 * Created by lmq.
 * Date: 2022/6/21
 */
public class ServerException extends Exception{
    private String code;

    public ServerException(String message, String code) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
