package com.coinex.sdk.rest.interfaces.retrofit;

import com.coinex.sdk.rest.response.CoinexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:23 PM
 */
public interface MarketAPIRetrofit {

    @GET("market/info")
    Call<CoinexResponse<LinkedHashMap>> getAllMarketInfo();

    @GET("market/kline")
    Call<CoinexResponse<List<List<Object>>>> getKlineMarket(
            @Query("market") String market, @Query("type") String type, @Query("limit")
    int limit);

    @GET("market/list")
    Call<CoinexResponse<List<String>>> getAllMarkets();
}
