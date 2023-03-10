package com.digua.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionUtils {

  /**
   * 获取自己应用内部的版本号
   */
  public static int getVersionCode(Context context) {
    PackageManager manager = context.getPackageManager();
    int code = 0;
    try {
      PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
      code = info.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return code;
  }

  /**
   * 获取自己应用内部的版本名
   */
  public static String getVersionName(Context context) {
    PackageManager manager = context.getPackageManager();
    String name = null;
    try {
      PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
      name = info.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    return name;
  }


}
