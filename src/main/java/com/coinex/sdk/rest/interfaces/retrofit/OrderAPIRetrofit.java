package com.coinex.sdk.rest.interfaces.retrofit;

import com.coinex.sdk.rest.request.OrderCreateApiRequest;
import com.coinex.sdk.rest.response.CoinexResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.LinkedHashMap;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:23 PM
 */
public interface OrderAPIRetrofit {

    @POST("order/market")
    Call<CoinexResponse<LinkedHashMap>> createMarketOrder(
            @Header("authorization") String secret
            , @Body OrderCreateApiRequest opsRequest);

}
