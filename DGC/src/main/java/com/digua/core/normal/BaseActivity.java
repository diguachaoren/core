package com.digua.core.normal;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B mBinding;

    private Set<String> cachePermissions;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this),
                getLayoutId(), null, false);
        setContentView(mBinding.getRoot());
        if (onPermissions() != null) {
            cachePermissions = Arrays.stream(onPermissions()).collect(Collectors.toSet());
            open_permission().launch(onPermissions());
        } else {
            initData();
            initView();
        }
    }

    protected String[] onPermissions() {
        return null;
    }

    protected void initView() {

    }

    protected abstract int getLayoutId();

    protected abstract void initData();


    public ActivityResultLauncher<String[]> open_permission() {
        return registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {

                    /*读文件权限*/
                    if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (Objects.requireNonNull(result.get(Manifest.permission.READ_EXTERNAL_STORAGE)).equals(true)
                        ) {
                            //权限全部获取到之后的动作
                            checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE);
                        } else {
                            //有权限没有获取到的动作
                        }
                    }

                    /*写文件权限*/
                    if (result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if (Objects.requireNonNull(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE)).equals(true)
                        ) {
                            //权限全部获取到之后的动作
                            checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        } else {
                            //有权限没有获取到的动作
                        }
                    }

                    /*相机权限*/
                    if (result.get(Manifest.permission.CAMERA) != null) {
                        if (Objects.requireNonNull(result.get(Manifest.permission.CAMERA)).equals(true)) {
                            //权限全部获取到之后的动作
                            checkPermissions(Manifest.permission.CAMERA);
                        } else {
                            //有权限没有获取到的动作
                        }
                    }

                    if(result.get(Manifest.permission.SYSTEM_ALERT_WINDOW) != null){
                        if (Objects.requireNonNull(result.get(Manifest.permission.SYSTEM_ALERT_WINDOW)).equals(true)) {
                            //权限全部获取到之后的动作
                            checkPermissions(Manifest.permission.SYSTEM_ALERT_WINDOW);
                        } else {
                            //有权限没有获取到的动作
                        }
                    }


                });
    }


    private void checkPermissions(String permission) {
        if (cachePermissions.contains(permission)) {
            cachePermissions.remove(permission);
        }

        if(cachePermissions.size() == 0){
            initData();
            initView();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
    }
}
