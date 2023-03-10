package com.digua.core.normal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleAdapter<VH extends BaseViewHolder, S> extends RecyclerView.Adapter<VH> {
    public List<S> list;
    public Context context;
    public OnItemClick onItemClick;
    public OnItemLongClick onItemLongClick;


    public BaseRecycleAdapter(Context context) {
        this.context = context;
        /*解决刷新数据闪烁问题*/
        setHasStableIds(true);
    }

    public BaseRecycleAdapter(List<S> list, Context context) {
        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
        }
        this.list = list;
        this.context = context;
    }

    public void newData(List<S> list) {
        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
        }
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void setData(List<S> s) {
        if (s == null) return;
        int lastIndex = 0;
        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
        }
        this.list.addAll(s);
        lastIndex = list.size();
        this.notifyItemChanged(lastIndex);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public interface OnItemLongClick {
        void OnItemLongClick(int position);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH v = null;
        try {
            v = getVClass()
                    .getConstructor(getVBClass()).newInstance(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            layoutId(), parent, false));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, @SuppressLint("RecyclerView") int position) {
        if (onItemClick != null) {
            holder.mBinding.getRoot().setOnClickListener(v -> onItemClick.onItemClick(position));
        }
        if (onItemLongClick != null) {
            holder.mBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClick.OnItemLongClick(position);
                    return true;
                }
            });
        }
        initViewHolder(holder, position);
    }

    protected abstract void initViewHolder(@NonNull VH holder, int position);

    public abstract Class<VH> getVClass();

    public abstract Class<? extends ViewDataBinding> getVBClass();

    public abstract int layoutId();

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


}
