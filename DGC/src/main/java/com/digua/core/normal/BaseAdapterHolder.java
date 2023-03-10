package com.digua.core.normal;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseAdapterHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

    V mBinding;

    public BaseAdapterHolder(V mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public V getBinding() {
        return mBinding;
    }
}