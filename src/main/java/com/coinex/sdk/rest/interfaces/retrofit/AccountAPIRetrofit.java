package com.coinex.sdk.rest.interfaces.retrofit;

import com.coinex.sdk.rest.request.WithdrawApplyRequest;
import com.coinex.sdk.rest.response.CoinexResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:23 PM
 */
public interface AccountAPIRetrofit {

    @GET("balance/coin/withdraw")
    Call<CoinexResponse<List<LinkedHashMap>>> getWithdrawalHistory(
            @Header("authorization") String secret,
            @Query("access_id") String access_id,
            @Query("tonce") String tonce,
            @Query("page") Integer page, @Query("limit") Integer limit
    );

    @GET("balance/info")
    Call<CoinexResponse<LinkedHashMap>> getBalance(
            @Header("authorization") String secret,
            @Query("access_id") String access_id,
            @Query("tonce") String tonce
    );

    @POST("balance/coin/withdraw")
    Call<CoinexResponse<LinkedHashMap>> applyWithdraw(
            @Header("authorization") String secret,
            @Body WithdrawApplyRequest build);
}
