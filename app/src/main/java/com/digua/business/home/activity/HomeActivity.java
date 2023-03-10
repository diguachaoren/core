package com.digua.business.home.activity;


import com.digua.base.R;
import com.digua.base.databinding.HomeBinding;
import com.digua.business.home.viewmodel.HomeViewModel;
import com.digua.core.utils.StatusBarUtil;
import com.digua.core.utils.eventbus.Event;
import com.digua.core.vm.BaseVMActivity;

public class HomeActivity extends BaseVMActivity<HomeViewModel, HomeBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.home;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mBinding.setVm(model);
        mBinding.setLifecycleOwner(this);
        isCanPressBack(true);

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this);
        StatusBarUtil.setPaddingSmart(this, findViewById(R.id.title));


    }

    @Override
    protected Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public void Receive(Event event) {

    }
}