/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.interfaces.retrofit;

import com.coinex.sdk.rest.response.CoinexResponse;
import com.coinex.sdk.rest.response.WebsocketTokenResponse;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public interface WebsocketPublicAPIRetrofit {

    @POST("api/v1/bullet-public")
    Call<CoinexResponse<WebsocketTokenResponse>> getPublicToken();

}
