package com.digua.business.main;

import android.view.View;

import com.digua.base.R;
import com.digua.base.databinding.MainActivityBinding;
import com.digua.business.login.activity.LoginActivity;
import com.digua.business.test1.activity.RxJavaTestActivity;
import com.digua.business.test2.activity.CodeTestActivity;
import com.digua.core.utils.eventbus.Event;
import com.digua.core.vm.BaseVMActivity;

public class MainActivity extends BaseVMActivity<MainViewModel, MainActivityBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initData() {

        mBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(RxJavaTestActivity.class);
            }
        });

        mBinding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(CodeTestActivity.class);
            }
        });

        mBinding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(LoginActivity.class);
            }
        });

    }

    @Override
    protected Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    public void Receive(Event event) {

    }
}
