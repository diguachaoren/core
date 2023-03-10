package com.digua.core.net;

public abstract class BaseObserver<T> extends RxObserver<T> {

    public BaseObserver() {

    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);
        e.printStackTrace();

    }


}
