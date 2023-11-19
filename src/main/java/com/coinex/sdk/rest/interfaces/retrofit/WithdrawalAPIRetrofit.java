/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.interfaces.retrofit;

import com.coinex.sdk.rest.request.WithdrawApplyRequest;
import com.coinex.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface WithdrawalAPIRetrofit {

    @GET("api/v1/withdrawals/quotas")
    Call<CoinexResponse<WithdrawQuotaResponse>> getWithdrawQuotas(@Query("currency") String currency,
                                                                  @Query("chain") String chain);

    @POST("api/v1/withdrawals")
    Call<CoinexResponse<WithdrawApplyResponse>> applyWithdraw(@Body WithdrawApplyRequest request);

    @DELETE("api/v1/withdrawals/{withdrawalId}")
    Call<CoinexResponse<Void>> cancelWithdraw(@Path("withdrawalId") String withdrawalId);

    @GET("api/v1/withdrawals")
    Call<CoinexResponse<Pagination<WithdrawResponse>>> getWithdrawPageList(@Query("currentPage") int currentPage,
                                                                           @Query("pageSize") int pageSize);
}
