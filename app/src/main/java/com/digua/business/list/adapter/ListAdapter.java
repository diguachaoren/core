package com.digua.business.list.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.digua.base.R;
import com.digua.base.databinding.ItemListBinding;
import com.digua.business.list.viewholder.ListViewHolder;
import com.digua.core.normal.BaseRecycleAdapter;
import com.digua.repository.net.company.bean.GetAccountFlowListResponse;

public class ListAdapter extends BaseRecycleAdapter<ListViewHolder, GetAccountFlowListResponse.AccountFlow> {

    public ListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void initViewHolder(@NonNull ListViewHolder holder, int position) {

    }

    @Override
    public Class<ListViewHolder> getVClass() {
        return ListViewHolder.class;
    }

    @Override
    public Class<? extends ViewDataBinding> getVBClass() {
        return ItemListBinding.class;
    }

    @Override
    public int layoutId() {
        return R.layout.item_list;
    }
}
