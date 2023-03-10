package com.digua.repository.net.company.bean;

/**
 * Created by lmq.
 * Date: 2022/9/8
 */
public class LoginResponse {


    private Integer code;
    private String message;
    private DataDTO data;

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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        private UserInfoBean userInfo;
        private ShopBean shop;
        private Boolean initStatus;
        private String token;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public Boolean getInitStatus() {
            return initStatus;
        }

        public void setInitStatus(Boolean initStatus) {
            this.initStatus = initStatus;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


    }
}
