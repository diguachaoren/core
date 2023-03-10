package com.digua.core.vm;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.digua.core.R;


public abstract class BaseVMDialog<M extends BaseViewModel, V extends ViewDataBinding> extends DialogFragment {

    protected V mBinding;
    //点击其他区域是否关闭弹框
    private boolean mOutsideCancelable = true;

    private Integer gravity;

    private float zoom = 0.9f;

    protected M model;

    public int getGravity() {
        if (gravity == null) {
            gravity = Gravity.BOTTOM;
        }
        return gravity;
    }

    public void setmOutsideCancelable(boolean mOutsideCancelable) {
        this.mOutsideCancelable = mOutsideCancelable;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = (M) new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(getViewModel());
        initData();
        initView();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(mOutsideCancelable);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = getGravity();
            params.width = (int) (dm.widthPixels * zoom);
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
            window.setBackgroundDrawable(getContext().getDrawable(setBackgroundDrawable()));
        }
    }


    protected int setBackgroundDrawable() {
        return R.drawable.dialog_bg;
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected void initView() {
    }

    ;

    protected abstract Class<M> getViewModel();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
    }
}
