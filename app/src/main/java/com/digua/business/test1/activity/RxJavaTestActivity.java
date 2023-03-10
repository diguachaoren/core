package com.digua.business.test1.activity;

import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;

import com.digua.App;
import com.digua.Platform;
import com.digua.base.R;
import com.digua.base.databinding.RxjavatestActivityBinding;
import com.digua.business.test1.viewmodel.RxJavaTestViewModel;
import com.digua.core.utils.LogUtils;
import com.digua.core.utils.ToastUntil;
import com.digua.core.utils.eventbus.Event;
import com.digua.core.utils.inputcode.BarcodeScannerResolver;
import com.digua.core.vm.BaseVMActivity;

public class RxJavaTestActivity extends BaseVMActivity<RxJavaTestViewModel, RxjavatestActivityBinding> {

    private BarcodeScannerResolver mBarcodeScannerResolver;

    @Override
    protected int getLayoutId() {
        return R.layout.rxjavatest_activity;
    }

    @Override
    protected void initData() {
        mBinding.setVm(model);
        mBinding.setLifecycleOwner(this);
        isCanPressBack(true);

        // 监听
        startScanListen();

        if (App.platform == Platform.WOSHI) {
            mBinding.content.setText("卧式");
        } else if (App.platform == Platform.DOUBLE_SCREEN) {
            mBinding.content.setText("双屏");
        }

        mBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.requestNum.set(false);
                model.msg.set("");
                model.initObservable();
                model.merge();
            }
        });

        mBinding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.requestNum.set(false);
                model.msg.set("");
                model.initObservable();
                model.concat();
            }
        });

    }


    /**
     * 点击开始扫码监听按钮
     */
    public void startScanListen() {
        mBarcodeScannerResolver = new BarcodeScannerResolver();
        mBarcodeScannerResolver.setScanSuccessListener(new BarcodeScannerResolver.OnScanSuccessListener() {
            @Override
            public void onScanSuccess(String barcode) {
                String content = "编码==" + barcode + "====";
                LogUtils.e(content);
                ToastUntil.getInstance().showToast(content);
            }
        });
    }


    /**
     * 点击移除扫码监听按钮
     */
    public void removeScanListen() {
        mBarcodeScannerResolver.removeScanSuccessListener();
        mBarcodeScannerResolver = null;
    }


    /**
     * 扫码枪是输入设备，检测是否有外接输入设备.(这样判断其实并不严格)
     *
     * @return
     */
    private boolean hasScanGun() {
        Configuration cfg = getResources().getConfiguration();
        return cfg.keyboard != Configuration.KEYBOARD_NOKEYS;
    }

//

    /**
     * Activity截获按键事件.发给 BarcodeScannerResolver
     * dispatchKeyEvent() 和 onKeyDown() 方法均可
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        LogUtils.e("dispatchKeyEvent");

        if (hasScanGun()) {
            mBarcodeScannerResolver.resolveKeyEvent(event);
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected Class<RxJavaTestViewModel> getViewModel() {
        return RxJavaTestViewModel.class;
    }

    @Override
    public void Receive(Event event) {

    }

    //<editor-fold desc="测试方法标注">
    public void test() {

    }
}
