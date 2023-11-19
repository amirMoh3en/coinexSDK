/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.adapter;

import com.coinex.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.coinex.sdk.rest.interfaces.OrderAPI;
import com.coinex.sdk.rest.interfaces.retrofit.OrderAPIRetrofit;
import com.coinex.sdk.rest.request.OrderCreateApiRequest;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class OrderApiAdapter extends PublicRetrofitAPIImpl<OrderAPIRetrofit> implements OrderAPI {

    public OrderApiAdapter(String baseUrl ) {
        this.baseUrl = baseUrl;
    }

    @Override
    public LinkedHashMap createMarketOrder(String secret ,
                                           OrderCreateApiRequest opsRequest) throws IOException {
        return executeSync(getAPIImpl().createMarketOrder(secret , opsRequest));
    }
}
