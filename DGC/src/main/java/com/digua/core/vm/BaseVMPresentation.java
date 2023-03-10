package com.digua.core.vm;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * Created by lmq.
 * Date: 2022/10/19
 * 异显
 */
public abstract class BaseVMPresentation<V extends BaseViewModel, B extends ViewDataBinding> extends Presentation {

    protected B mBinding;

    protected V model;

    public BaseVMPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                getLayoutId(), null, false);
        setContentView(mBinding.getRoot());
        model = (V) new ViewModelProvider((ViewModelStoreOwner) getOwnerActivity(), new ViewModelProvider.NewInstanceFactory()).get(getViewModel());
        initData();
        initView();
    }

    protected void initView() {

    }

    protected abstract Class<V> getViewModel();

    protected abstract int getLayoutId();

    protected abstract void initData();

}
