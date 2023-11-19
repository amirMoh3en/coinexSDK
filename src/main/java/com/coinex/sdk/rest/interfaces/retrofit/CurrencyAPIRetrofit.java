package com.coinex.sdk.rest.interfaces.retrofit;

import com.coinex.sdk.rest.response.CoinexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.LinkedHashMap;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:23 PM
 */
public interface CurrencyAPIRetrofit {

    @GET("common/asset/config")
    Call<CoinexResponse<LinkedHashMap>> getCoinDetails();

    @GET("common/asset/config")
    Call<CoinexResponse<LinkedHashMap>> getCoinDetails(@Query("coin_type") String coinCode);
}
