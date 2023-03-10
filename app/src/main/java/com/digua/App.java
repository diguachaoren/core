package com.digua;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.multidex.MultiDexApplication;

import com.digua.base.BuildConfig;
import com.digua.core.utils.LogUtils;
import com.digua.core.utils.ToastUntil;

public class App extends MultiDexApplication {

    private static UserStorage userStorage;

    private static App app;

    public static Platform platform;

    @Override
    public void onCreate() {
        super.onCreate();
        // this app
        app = this;
        // 初始化 LogUtils
        LogUtils.init(BuildConfig.DEBUG);
        // 初始化 Toast
        ToastUntil.getInstance().register(this);
        // 初始化userStorage
        userStorage = new UserStorage(this);
        // 平台认定
        CHECK_PLATFORM();
        // Base url
        CHECK_BASEURL();
    }


    private void CHECK_PLATFORM() {
        String channelContent = getChannelFromAndroidManifest();
        if (channelContent.equals("double_screen")) {
            platform = Platform.DOUBLE_SCREEN;
        } else if (channelContent.equals("woshi_screen")) {
            platform = Platform.WOSHI;
        } else {
            platform = Platform.DOUBLE_SCREEN;
        }
    }

    private void CHECK_BASEURL(){
        if (BuildConfig.FLAVOR.equals("卧式")){
            if (BuildConfig.DEBUG){
                Constants.Base_Url = Constants.BASE_URL_WOSHI_DEBUG;
            }else {
                Constants.Base_Url = Constants.BASE_URL_WOSHI_RELEASE;
            }
        }else if(BuildConfig.FLAVOR.equals("双屏")){
            if (BuildConfig.DEBUG){
                Constants.Base_Url = Constants.BASE_URL_DOUBLE_SCREEN_DEBUG;
            }else {
                Constants.Base_Url = Constants.BASE_URL_DOUBLE_SCREEN_RELEASE;
            }
        }
    }


    private String getChannelFromAndroidManifest() {
        String metaChannel = "";
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if (info == null || info.metaData == null) {
                return metaChannel;
            }
            metaChannel = info.metaData.getString("channel");
        } catch (PackageManager.NameNotFoundException e) {
        }
        return metaChannel;
    }


    public static App getInstance() {
        return app;
    }


    public UserStorage getUserStorage() {
        return userStorage;
    }

}
