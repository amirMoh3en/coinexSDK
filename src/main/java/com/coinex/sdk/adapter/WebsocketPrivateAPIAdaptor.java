///**
// * Copyright 2019 Mek Global Limited.
// */
//package com.coinex.sdk.adapter;
//
//import com.coinex.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
//import com.coinex.sdk.rest.interfaces.WebsocketPrivateAPI;
//import com.coinex.sdk.rest.interfaces.retrofit.WebsocketAuthAPIRetrofit;
//import ir.vistaapp.backend.com.kucoin.sdk.rest.response.WebsocketTokenResponse;
//
//import java.io.IOException;
//
///**
// * Created by chenshiwei on 2019/1/18.
// */
//public class WebsocketPrivateAPIAdaptor extends AuthRetrofitAPIImpl<WebsocketAuthAPIRetrofit>
//        implements WebsocketPrivateAPI {
//
//    public WebsocketPrivateAPIAdaptor(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
//        this.baseUrl = baseUrl;
//        this.apiKey = apiKey;
//        this.secret = secret;
//        this.passPhrase = passPhrase;
//        this.apiKeyVersion = apiKeyVersion;
//    }
//
//    @Override
//    public WebsocketTokenResponse getPrivateToken() throws IOException {
//        return super.executeSync(getAPIImpl().getPrivateToken());
//    }
//}
