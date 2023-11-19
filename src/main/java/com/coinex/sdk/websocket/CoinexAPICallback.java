/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.websocket;

import com.coinex.sdk.exception.CoinexApiException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface CoinexAPICallback<T> {

    void onResponse(T response) throws CoinexApiException;


}
