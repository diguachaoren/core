package com.digua.repository.net.company;



import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.digua.repository.net.company.bean.GetShopListResponse;
import com.digua.repository.net.company.bean.GetUnsampledListResponse;
import com.digua.repository.net.company.bean.LoginRequest;
import com.digua.repository.net.company.bean.LoginResponse;

/**
 * Created by lmq.
 * Date: 2022/8/24
 */
public interface ApiService {

    /**
     * 登录
     */
    @POST("ict/login")
    Observable<LoginResponse> login(@Body LoginRequest request);

    /**
     * 获取未取样列表
     */
    @GET("rest-sample/getUnsampledList")
    Observable<GetUnsampledListResponse> getUnsampledList(@Query("date") String date,
                                                          @Query("shopId") String shopId,
                                                          @Query("foodPeriodId") String foodPeriodId);


    /**
     * 获取食堂列表
     */
    @GET("eihoojk/shop/getShopList")
    Observable<GetShopListResponse> getShopList();


}
