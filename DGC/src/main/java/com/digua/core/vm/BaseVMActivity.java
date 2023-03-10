package com.digua.core.vm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.digua.core.utils.EmptyUtils;
import com.digua.core.utils.eventbus.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Set;

import es.dmoral.toasty.Toasty;

public abstract class BaseVMActivity<V extends BaseViewModel, B extends ViewDataBinding>
        extends AppCompatActivity {

    protected B mBinding;

    protected V model;

    protected Set<String> cachePermissions;

    protected ProgressDialog progressBar;

    protected Context mContext;

    private boolean isCanPressBack = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 状态栏透明
        // StatusBarUtil.immersive(this, 0xff000000, 0.1f);
        mContext = this;
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this),
                getLayoutId(), null, false);

        model = (V) new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(
                getViewModel());

        setContentView(mBinding.getRoot());

        initCompone();
        initData();
        initView();
    }

    protected void isCanPressBack(boolean _isCanPressBack) {
        isCanPressBack = _isCanPressBack;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isCanPressBack) {
            finish();
            return true;
        } else {
            return false;
        }
    }


    /**
     * 初始化基础控件
     * Progress
     * Toast
     * JumpActivity
     */
    private void initCompone() {
        progressBar = new ProgressDialog(this);

        model.baseProgressBar.observe(this,
                status -> {
                    if (status) {
                        if (EmptyUtils.isNotEmpty(progressBar)) {
                            progressBar.show();
                        }
                    } else {
                        if (EmptyUtils.isNotEmpty(progressBar) && progressBar.isShowing()) {
                            progressBar.dismiss();
                        }
                    }
                }
        );

        model.baseToast.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toasty.normal(mContext, s).show();
            }
        });

    }



    /**
     * 通过类名启动Activity add
     */
    protected void jumpActivity(Class<?> pClass) {
        jumpActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     */
    protected void jumpActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    protected void initView() {

    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract Class<V> getViewModel();


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Dialog生命周期
        if (EmptyUtils.isNotEmpty(progressBar) && progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
        if (EmptyUtils.isNotEmpty(progressBar) && progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            Receive(event);
        }
    }

    public abstract void Receive(Event event);
}
