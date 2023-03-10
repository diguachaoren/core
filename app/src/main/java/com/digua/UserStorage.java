package com.digua;

import android.content.Context;

import com.digua.core.utils.SPUtil;
import com.digua.repository.net.company.bean.ShopBean;
import com.digua.repository.net.company.bean.UserInfoBean;


public class UserStorage {

    private Context mContext;

    public UserStorage(Context context) {
        this.mContext = context;
    }

    /*
     *  获取用户信息
     * */
    public UserInfoBean getUserInfo() {
        UserInfoBean userInfoBean =
                SPUtil.getValueObject(mContext, Constants.SP_USERINFO_OBJ, UserInfoBean.class);
        return userInfoBean;
    }


    /*
     *  保存用户信息
     * */
    public void setUserInfo(UserInfoBean userInfo) {
        SPUtil.putValueObject(mContext, Constants.SP_USERINFO_OBJ, userInfo);
    }

    /*
     *  获取店铺信息
     * */
    public ShopBean getShop() {
        ShopBean userInfoBean =
                SPUtil.getValueObject(mContext, Constants.SP_SHOP_OBJ, ShopBean.class);
        return userInfoBean;
    }


    /*
     *  保存店铺信息
     * */
    public void setShop(ShopBean content) {
        SPUtil.putValueObject(mContext, Constants.SP_SHOP_OBJ, content);
    }


    /*
     *  获取记住密码信息
     * */
    public boolean getIsRemember() {
        boolean content =
                SPUtil.getValue(mContext, Constants.SP_IS_REMEMBER, false);
        return content;
    }


    /*
     *  设置记住密码信息
     * */
    public void setIsRemember(boolean content) {
        SPUtil.putValue(mContext, Constants.SP_IS_REMEMBER, content);
    }


}
