/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.adapter;

import com.coinex.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.coinex.sdk.rest.interfaces.CurrencyAPI;
import com.coinex.sdk.rest.interfaces.retrofit.CurrencyAPIRetrofit;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class CurrencyApiAdapter extends PublicRetrofitAPIImpl<CurrencyAPIRetrofit> implements CurrencyAPI {

    public CurrencyApiAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public LinkedHashMap getCoinDetails() throws IOException {
        return executeSync(getAPIImpl().getCoinDetails());
    }

    @Override
    public LinkedHashMap getCoinDetails(String coinCode) throws IOException {
        return executeSync(getAPIImpl().getCoinDetails(coinCode));
    }
}
