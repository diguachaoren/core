package com.digua.business.login.activity;


import androidx.lifecycle.Observer;

import com.digua.base.R;
import com.digua.base.databinding.LoginBinding;
import com.digua.business.login.viewmodel.LoginViewModel;
import com.digua.core.utils.StatusBarUtil;
import com.digua.core.utils.eventbus.Event;
import com.digua.core.vm.BaseVMActivity;

public class LoginActivity extends BaseVMActivity<LoginViewModel, LoginBinding> {

    @Override
    public void Receive(Event event) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mBinding.setVm(model);
        mBinding.setLifecycleOwner(this);
        isCanPressBack(true);
        // 透明title
        StatusBarUtil.immersive(this, 0xff000000, 0.1f);

        model.action.observe(this, new Observer<LoginViewModel.ACTION_TYPE>() {
            @Override
            public void onChanged(LoginViewModel.ACTION_TYPE action) {

                switch (action) {
                    case SHIBIE:
                        break;
                    case REGISTER:
                        break;
                }

            }
        });




    }

    @Override
    protected Class<LoginViewModel> getViewModel() {
        return LoginViewModel.class;
    }
}