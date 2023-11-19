/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk;

import com.coinex.sdk.rest.interfaces.AccountAPI;
import com.coinex.sdk.rest.interfaces.CurrencyAPI;
import com.coinex.sdk.rest.interfaces.MarketAPI;
import com.coinex.sdk.rest.interfaces.OrderAPI;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface CoinexRestClient {
    MarketAPI marketAPI();
    CurrencyAPI currencyApi();
    OrderAPI orderApi();
    AccountAPI accountApi();

}
