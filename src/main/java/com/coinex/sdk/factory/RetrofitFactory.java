/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.factory;

import com.coinex.sdk.CoinexObjectMapper;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public class RetrofitFactory {

    private static final Converter.Factory CONVERTER_FACTORY = JacksonConverterFactory.create(CoinexObjectMapper.INSTANCE);

    public static Retrofit getPublicRetorfit(String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(CONVERTER_FACTORY)
                .client(HttpClientFactory.getPublicClient())
                .build();

    }

//    public static Retrofit getAuthRetorfit(String baseUrl, String secret) {
//
//        return new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(CONVERTER_FACTORY)
//                .client(HttpClientFactory.getAuthClient(secret))
//                .build();
//
//    }
}