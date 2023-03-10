package com.digua.core.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/**
 */
public class ToastUntil {

    private static ToastUntil Instance;

    private Context context;

    /*不允许外部实例化调用*/
    private ToastUntil() {
    }

    /*需要在Application中先执行注册*/
    public void register(Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.context = context;
    }

    public static ToastUntil getInstance() {
        if (Instance == null) {
            Instance = new ToastUntil();
        }
        return Instance;
    }

    /*正常输出信息*/
    public void showToast(String message) {
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /*上部输出信息*/
    public void showTopToast(String message) {
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                });
    }

    /*中部输出信息*/
    public void showCenterToast(String message) {
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

    }


}
