/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.websocket;

import com.coinex.sdk.exception.CoinexApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenshiwei on 2019/1/19.
 */
public class PrintCallback<T> implements CoinexAPICallback<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCallback.class);

    @Override
    public void onResponse(T response) throws CoinexApiException {
        LOGGER.debug("Got response: {}", response);
    }

}
