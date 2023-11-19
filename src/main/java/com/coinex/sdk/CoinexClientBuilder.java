/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk;

import com.coinex.sdk.constants.APIConstants;
import com.coinex.sdk.impl.CoinexRestClientImpl;
import com.coinex.sdk.rest.adapter.AccountApiAdapter;
import com.coinex.sdk.rest.adapter.CurrencyApiAdapter;
import com.coinex.sdk.rest.adapter.MarketApiAdapter;
import com.coinex.sdk.rest.adapter.OrderApiAdapter;
import com.coinex.sdk.rest.interfaces.AccountAPI;
import com.coinex.sdk.rest.interfaces.CurrencyAPI;
import com.coinex.sdk.rest.interfaces.MarketAPI;
import com.coinex.sdk.rest.interfaces.OrderAPI;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by chenshiwei on 2019/1/9.
 */
@Getter
public class CoinexClientBuilder {

    private String baseUrl;

    private MarketAPI marketApi;
    private CurrencyAPI currencyAPI;
    private OrderAPI orderAPI;
    private AccountAPI accountAPI;





    public CoinexRestClient buildRestClient() {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL.getValue();
        if(marketApi == null) marketApi = new MarketApiAdapter(baseUrl);
        if(currencyAPI == null) currencyAPI = new CurrencyApiAdapter(baseUrl);
        if(orderAPI == null) orderAPI = new OrderApiAdapter(baseUrl);
        if(accountAPI == null) accountAPI = new AccountApiAdapter(baseUrl);

        return new CoinexRestClientImpl(this);
    }

    public CoinexClientBuilder withBaseUrl(String baseUrl, String secretKey) {
        this.baseUrl = baseUrl;
        return this;
    }


}
