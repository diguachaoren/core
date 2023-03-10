package com.digua.business.list.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.digua.core.vm.BaseViewModel;


public class ListViewModel extends BaseViewModel {


    public MutableLiveData<ListViewModel.ACTION_TYPE> action;


    public enum ACTION_TYPE {
        BACK, // 返回
        CHANGE_LAYOUT
    }


    public ListViewModel() {
        action = new MutableLiveData();
    }


    /**
     * 事件汇总
     */
    public void action(ListViewModel.ACTION_TYPE type) {
        switch (type) {
            case BACK:
                action.postValue(ListViewModel.ACTION_TYPE.BACK);
                break;
            case CHANGE_LAYOUT:
                action.postValue(ListViewModel.ACTION_TYPE.CHANGE_LAYOUT);
                break;
        }
    }



    /**
     * 回收
     */
    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
