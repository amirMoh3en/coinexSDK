///**
// * Copyright 2019 Mek Global Limited.
// */
//package com.coinex.sdk.impl;
//
//import com.coinex.sdk.CoinexClientBuilder;
//import com.coinex.sdk.KucoinPrivateWSClient;
//import com.coinex.sdk.constants.APIConstants;
//import com.coinex.sdk.model.enums.PrivateChannelEnum;
//import com.coinex.sdk.rest.interfaces.WebsocketPrivateAPI;
//import com.coinex.sdk.rest.response.WebsocketTokenResponse;
//import com.coinex.sdk.websocket.ChooseServerStrategy;
//import com.coinex.sdk.websocket.CoinexAPICallback;
//import com.coinex.sdk.websocket.event.*;
//import com.coinex.sdk.websocket.impl.BaseWebsocketImpl;
//import com.coinex.sdk.websocket.listener.KucoinPrivateWebsocketListener;
//import ir.vistaapp.backend.com.kucoin.sdk.websocket.event.*;
//import okhttp3.OkHttpClient;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
///**
// * Created by chenshiwei on 2019/1/18.
// */
//public class KucoinPrivateWSClientImpl extends BaseWebsocketImpl implements KucoinPrivateWSClient {
//
//    private final WebsocketPrivateAPI websocketPrivateAPI;
//    private final KucoinPrivateWebsocketListener listener;
//
//    public KucoinPrivateWSClientImpl(CoinexClientBuilder kucoinClientBuilder, KucoinPrivateWebsocketListener kucoinPrivateWebsocketListener, OkHttpClient okHttpClient) {
//        this(
//                okHttpClient,
//                kucoinPrivateWebsocketListener,
//                kucoinClientBuilder.getChooseServerStrategy(),
//                new WebsocketPrivateAPIAdaptor(kucoinClientBuilder.getBaseUrl(),
//                        kucoinClientBuilder.getAccessId(),
//                        kucoinClientBuilder.getSecretKey(),
//                        kucoinClientBuilder.getPassPhrase(),
//                        kucoinClientBuilder.getApiKeyVersion()));
//    }
//
//    private KucoinPrivateWSClientImpl(OkHttpClient client,
//                                      KucoinPrivateWebsocketListener listener,
//                                      ChooseServerStrategy chooseServerStrategy,
//                                      WebsocketPrivateAPI websocketPublicAPI) {
//        super(client, listener, chooseServerStrategy);
//        this.listener = listener;
//        this.websocketPrivateAPI = websocketPublicAPI;
//    }
//
//    @Override
//    protected WebsocketTokenResponse requestToken() throws IOException {
//        return this.websocketPrivateAPI.getPrivateToken();
//    }
//
//    @Override
//    @Deprecated
//    public String onOrderActivate(CoinexAPICallback<KucoinEvent<OrderActivateEvent>> callback, String... symbols) {
//        if (callback != null) {
//            this.listener.setOrderActivateCallback(callback);
//        }
//        String topic = APIConstants.API_ACTIVATE_TOPIC_PREFIX.getValue() + String.join(",", symbols);
//        return subscribe(topic, true, true);
//    }
//
//    @Override
//    public String onAccountBalance(CoinexAPICallback<KucoinEvent<AccountChangeEvent>> callback) {
//        if (callback != null) {
//            this.listener.setAccountChangeCallback(callback);
//        }
//        return subscribe(APIConstants.API_BALANCE_TOPIC_PREFIX.getValue(), true, true);
//    }
//
//    @Override
//    public String onOrderChange(CoinexAPICallback<KucoinEvent<OrderChangeEvent>> callback, String... symbols) {
//        if (callback != null) {
//            this.listener.setOrderChangeCallback(callback);
//        }
//        return subscribe(APIConstants.API_ORDER_TOPIC_PREFIX.getValue(), true, true);
//    }
//
//    @Override
//    public String onAdvancedOrder(CoinexAPICallback<KucoinEvent<? extends AdvancedOrderEvent>> callback, String... symbols) {
//        if (callback != null) {
//            this.listener.setAdvancedOrderCallback(callback);
//        }
//        return subscribe(APIConstants.API_ADVANCED_ORDER_TOPIC_PREFIX.getValue(), true, true);
//    }
//
//    @Override
//    public String ping(String requestId) {
//        return super.ping(requestId);
//    }
//
//    @Override
//    public String unsubscribe(PrivateChannelEnum channelEnum, String... symbols) {
//        return super.unsubscribe(channelEnum.getTopicPrefix() + Arrays.stream(symbols).collect(Collectors.joining(",")),
//                true, true);
//    }
//}
