/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.adapter;

import com.coinex.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.coinex.sdk.rest.interfaces.AccountAPI;
import com.coinex.sdk.rest.interfaces.retrofit.AccountAPIRetrofit;
import com.coinex.sdk.rest.request.WithdrawApplyRequest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class AccountApiAdapter extends PublicRetrofitAPIImpl<AccountAPIRetrofit> implements AccountAPI {

    public AccountApiAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<LinkedHashMap> getWithdrawalHistory(String secret, String access_id, String tonce, Integer page, Integer limit) throws IOException {
        return executeSync(getAPIImpl().getWithdrawalHistory(secret, access_id, tonce, page, limit));
    }

    @Override
    public LinkedHashMap getBalance(String secret, String access_id, String tonce) throws IOException {
        return executeSync(getAPIImpl().getBalance(secret, access_id, tonce));
    }

    @Override
    public LinkedHashMap applyWithdraw(String secret, WithdrawApplyRequest build) throws IOException {
        return executeSync(getAPIImpl().applyWithdraw(secret, build));
    }
}
