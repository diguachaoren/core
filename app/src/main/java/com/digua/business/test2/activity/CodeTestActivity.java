package com.digua.business.test2.activity;

import android.content.Context;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;

import com.digua.base.R;
import com.digua.base.databinding.CodetestActivityBinding;
import com.digua.business.test2.viewmodel.CodeTestViewModel;
import com.digua.core.utils.CodeUtils;
import com.digua.core.utils.ScannerEditText;
import com.digua.core.utils.eventbus.Event;
import com.digua.core.vm.BaseVMActivity;

public class CodeTestActivity extends BaseVMActivity<CodeTestViewModel, CodetestActivityBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.codetest_activity;
    }

    @Override
    protected void initData() {
        mBinding.setVm(model);
        mBinding.setLifecycleOwner(this);
        isCanPressBack(true);

        // 获取焦点不弹出键盘
        mBinding.findCode.setInputType(InputType.TYPE_NULL);
        InputMethodManager imm =
                (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mBinding.findCode.getWindowToken(), 0);

        mBinding.findCode.setScanResultListener(new ScannerEditText.ScanResultListener() {
            @Override
            public void onScanCompleted(String code) {
                String result = code.substring(0, code.length() - 1);
                String pay = "";

                switch (CodeUtils.check(result)) {
                    case WECHAT_CODE:
                        pay = "微信";
                        break;
                    case ALIPAY_CODE:
                        pay = "支付宝";
                        break;
                    case OTHER_CODE:
                        pay = "其他";
                        break;
                }


                mBinding.content.setText(pay + result);
            }
        });

    }


    @Override
    protected Class<CodeTestViewModel> getViewModel() {
        return CodeTestViewModel.class;
    }

    @Override
    public void Receive(Event event) {

    }


}
