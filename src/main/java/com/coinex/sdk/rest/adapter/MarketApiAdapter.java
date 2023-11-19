/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.adapter;

import com.coinex.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.coinex.sdk.rest.interfaces.MarketAPI;
import com.coinex.sdk.rest.interfaces.retrofit.MarketAPIRetrofit;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class MarketApiAdapter extends PublicRetrofitAPIImpl<MarketAPIRetrofit> implements MarketAPI {

    public MarketApiAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public LinkedHashMap getAllmarketInfo() throws IOException {
        return executeSync(getAPIImpl().getAllMarketInfo());
    }

    @Override
    public List<List<Object>> getKlineMarket(String market, int limit, String type) throws IOException {
        return executeSync(getAPIImpl().getKlineMarket(market, type, limit));
    }

    @Override
    public List<String> getAllMarkets() throws IOException {
        return executeSync(getAPIImpl().getAllMarkets());
    }
}
