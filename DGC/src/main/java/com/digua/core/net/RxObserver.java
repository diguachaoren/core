package com.digua.core.net;


import com.digua.core.utils.ToastUntil;

import java.net.UnknownHostException;

import io.reactivex.observers.ResourceObserver;

/**
 * Created by lmq.
 * Date: 2022/6/21
 */
public abstract class RxObserver<T> extends ResourceObserver<T> {

  /**
   * 当不需要提示时 使用这个构造方法
   */
  public RxObserver() {
  }

  @Override
  public void onError(Throwable e) {
    if (e instanceof UnknownHostException) {
      ToastUntil.getInstance().showCenterToast("网络连接异常");
    } else {
      ToastUntil.getInstance().showCenterToast(e.getMessage());
    }

    onComplete();
  }

  @Override
  public void onComplete() {

  }
}
