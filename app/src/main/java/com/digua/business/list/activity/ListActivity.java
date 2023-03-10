package com.digua.business.list.activity;


import android.os.Handler;
import android.view.View;

import androidx.lifecycle.Observer;

import com.digua.base.R;
import com.digua.base.databinding.ListBinding;
import com.digua.business.list.viewmodel.ListViewModel;
import com.digua.core.utils.StatusBarUtil;
import com.digua.core.utils.eventbus.Event;
import com.digua.core.vm.BaseVMActivity;

public class ListActivity extends BaseVMActivity<ListViewModel, ListBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.list;
    }

    @Override
    protected void initData() {
        mBinding.setVm(model);
        mBinding.setLifecycleOwner(this);

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this);
        StatusBarUtil.setPaddingSmart(this, findViewById(R.id.title));


        model.action.observe(this, new Observer<ListViewModel.ACTION_TYPE>() {
            @Override
            public void onChanged(ListViewModel.ACTION_TYPE action_type) {
                switch (action_type) {
                    case CHANGE_LAYOUT:
                        // model.loadingData();
                        break;
                    case BACK:
                        finish();
                        break;
                }
            }
        });


        mBinding.title.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected Class<ListViewModel> getViewModel() {
        return ListViewModel.class;
    }

    @Override
    public void Receive(Event event) {

    }


}
