package com.digua.core.normal;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public V mBinding;

    public BaseViewHolder(V v) {
        super(v.getRoot());
        mBinding = v;
    }
}
