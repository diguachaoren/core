package com.digua.core.normal;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by lmq.
 * Date: 2022/10/19
 * 异显
 */
public abstract class BasePresentation<B extends ViewDataBinding> extends Presentation {

    protected B mBinding;

    public BasePresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                getLayoutId(), null, false);
        setContentView(mBinding.getRoot());
        initData();
        initView();
    }

    protected void initView() {

    }

    protected abstract int getLayoutId();

    protected abstract void initData();

}
