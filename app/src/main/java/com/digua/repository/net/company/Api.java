package com.digua.repository.net.company;


import com.digua.Constants;
import com.digua.base.net.HttpHelp;

public class Api {

  private ApiService apiService;

  private static Api api;

  private Api() {
    apiService = HttpHelp.Companion.getInstance().getServiceRetrofit(Constants.Base_Url, null)
        .create(ApiService.class);
  }

  public static Api getInstance() {
    if (api == null) {
      api = new Api();
    }
    return api;
  }

  public ApiService getApiService() {
    return apiService;
  }


}
