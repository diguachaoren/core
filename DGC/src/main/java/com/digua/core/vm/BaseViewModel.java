package com.digua.core.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.digua.core.utils.EmptyUtils;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by lmq.
 * Date: 2022/8/24
 */
public class BaseViewModel extends ViewModel {

  /* 显示，关闭 progress */
  protected MutableLiveData<Boolean> baseProgressBar;

  /* 显示Toast */
  protected MutableLiveData<String> baseToast;


  public BaseViewModel() {
    this.baseProgressBar = new MutableLiveData<Boolean>();
    this.baseToast = new MutableLiveData<String>();
  }

  public RequestBody createRequestBody(Object o) {
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
        new Gson().toJson(o));
  }

  @Override protected void onCleared() {
    super.onCleared();
    if (EmptyUtils.isNotEmpty(baseProgressBar)) {
      baseProgressBar.setValue(false);
    }

  }
}
