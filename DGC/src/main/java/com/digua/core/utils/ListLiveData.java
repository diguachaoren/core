package com.digua.core.utils;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListLiveData <T> extends LiveData<List<T>> {
    public void addAll(List<T> items) {
        if (getValue() != null && items != null) {
            getValue().addAll(items);
            setValue(getValue());
        }
    }

    public void clear() {
        if (getValue() != null) {
            getValue().clear();
            setValue(getValue());
        }
    }

    @Override public void setValue(List<T> value) {
        super.setValue(value);
    }

    @Nullable
    @Override public List<T> getValue() {
        return super.getValue();
    }
}
