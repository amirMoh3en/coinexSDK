/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.impl;

import com.coinex.sdk.CoinexClientBuilder;
import com.coinex.sdk.CoinexRestClient;
import com.coinex.sdk.rest.interfaces.AccountAPI;
import com.coinex.sdk.rest.interfaces.CurrencyAPI;
import com.coinex.sdk.rest.interfaces.MarketAPI;
import com.coinex.sdk.rest.interfaces.OrderAPI;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public class CoinexRestClientImpl implements CoinexRestClient {
    private final MarketAPI marketAPI;
    private final OrderAPI orderAPI;
    private final CurrencyAPI currencyAPI;
    private final AccountAPI accountApi;

    public CoinexRestClientImpl(CoinexClientBuilder kucoinBuilder) {
        this.marketAPI = kucoinBuilder.getMarketApi();
        this.orderAPI = kucoinBuilder.getOrderAPI();
        this.currencyAPI = kucoinBuilder.getCurrencyAPI();
        this.accountApi = kucoinBuilder.getAccountAPI();
    }

    @Override
    public MarketAPI marketAPI() {
        return marketAPI;
    }

    @Override
    public CurrencyAPI currencyApi() {
        return currencyAPI;
    }

    @Override
    public OrderAPI orderApi() {
        return orderAPI;
    }

    @Override
    public AccountAPI accountApi() {
        return accountApi;
    }

}
