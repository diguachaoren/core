package com.digua.core.vm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseVMFrament<V extends BaseViewModel,B extends ViewDataBinding> extends Fragment {

    protected B mBinding;

    protected V model;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        App.getRefWatcher().watch(this);
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()),
                getLayoutId(), null, false);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = (V) new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(getViewModel());
        initData();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract Class<V> getViewModel();

    protected void initView() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }
}
