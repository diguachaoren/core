package com.digua.business.login.activity;

import com.digua.base.R;
import com.digua.base.databinding.SplashBinding;
import com.digua.business.login.viewmodel.SplashViewModel;
import com.digua.core.utils.eventbus.Event;
import com.digua.core.vm.BaseVMActivity;

public class SplashActivity extends BaseVMActivity<SplashViewModel, SplashBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.splash;
    }

    @Override
    protected void initData() {
        mBinding.setVm(model);
        mBinding.setLifecycleOwner(this);
        isCanPressBack(true);
    }

    @Override
    protected Class<SplashViewModel> getViewModel() {
        return SplashViewModel.class;
    }

    @Override
    public void Receive(Event event) {

    }
}