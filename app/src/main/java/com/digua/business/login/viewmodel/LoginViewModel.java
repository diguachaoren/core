package com.digua.business.login.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.digua.core.vm.BaseViewModel;


public class LoginViewModel extends BaseViewModel {


    public MutableLiveData action;


    /* 事件 */
    public enum ACTION_TYPE {
        REGISTER,// 返回
        SHIBIE
    }

    public LoginViewModel() {
        this.action = new MutableLiveData();


    }


    /**
     * 事件汇总
     */
    public void action(LoginViewModel.ACTION_TYPE type) {
        switch (type) {
            case REGISTER:
                action.postValue(ACTION_TYPE.REGISTER);
                break;
            case SHIBIE:
                action.postValue(ACTION_TYPE.SHIBIE);
                break;
        }
    }



    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
