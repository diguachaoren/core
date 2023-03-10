package com.digua.core.normal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.digua.core.R;

public abstract class BasePopupWindow<B extends ViewDataBinding> extends PopupWindow {

    protected B mBinding;

    //点击其他区域是否关闭弹框
    private boolean mOutsideCancelable = true;

    public void setmOutsideCancelable(boolean mOutsideCancelable) {
        this.mOutsideCancelable = mOutsideCancelable;
    }

    public BasePopupWindow(Context context) {
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                getLayoutId(), null, false);
        setContentView(mBinding.getRoot());
    }


    @Override
    public void showAsDropDown(View anchor) {
        initData();
        initView();
        setFocusable(mOutsideCancelable);
        setBackgroundDrawable(anchor.getContext().getDrawable(setBackgroundDrawable()));
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        super.showAsDropDown(anchor);
    }

    protected void initView() {

    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected int setBackgroundDrawable() {
        return R.drawable.popup_window_bg;
    }
}
