///**
// * Copyright 2019 Mek Global Limited.
// */
//package com.coinex.sdk.impl;
//
//import com.coinex.sdk.CoinexClientBuilder;
//import com.coinex.sdk.KucoinPublicWSClient;
//import com.coinex.sdk.adapter.WebsocketPublicAPIAdaptor;
//import com.coinex.sdk.constants.APIConstants;
//import com.coinex.sdk.model.enums.PublicChannelEnum;
//import com.coinex.sdk.websocket.event.*;
//import com.coinex.sdk.rest.interfaces.WebsocketPublicAPI;
//import com.coinex.sdk.rest.response.WebsocketTokenResponse;
//import com.coinex.sdk.websocket.ChooseServerStrategy;
//import com.coinex.sdk.websocket.CoinexAPICallback;
//import com.coinex.sdk.websocket.impl.BaseWebsocketImpl;
//import com.coinex.sdk.websocket.listener.KucoinPublicWebsocketListener;
//import okhttp3.OkHttpClient;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
///**
// * Created by chenshiwei on 2019/1/17.
// */
//public class KucoinPublicWSClientImpl extends BaseWebsocketImpl implements KucoinPublicWSClient {
//
//    private final WebsocketPublicAPI websocketPublicAPI;
//    private final KucoinPublicWebsocketListener listener;
//
//    public KucoinPublicWSClientImpl(CoinexClientBuilder kucoinClientBuilder, KucoinPublicWebsocketListener kucoinPublicWebsocketListener, OkHttpClient okHttpClient) {
//        this(
//                okHttpClient,
//                kucoinPublicWebsocketListener,
//                kucoinClientBuilder.getChooseServerStrategy(),
//                new WebsocketPublicAPIAdaptor(kucoinClientBuilder.getBaseUrl()));
//    }
//
//    private KucoinPublicWSClientImpl(OkHttpClient client,
//                                     KucoinPublicWebsocketListener listener,
//                                     ChooseServerStrategy chooseServerStrategy,
//                                     WebsocketPublicAPI websocketPublicAPI) {
//        super(client, listener, chooseServerStrategy);
//        this.listener = listener;
//        this.websocketPublicAPI = websocketPublicAPI;
//    }
//
//    @Override
//    protected WebsocketTokenResponse requestToken() throws IOException {
//        return this.websocketPublicAPI.getPublicToken();
//    }
//
//    @Override
//    public String onTicker(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        if (callback != null) {
//            this.listener.setTickerCallback(callback);
//        }
//        String topic = APIConstants.API_TICKER_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
//        return subscribe(topic, false, true);
//    }
//
//    @Override
//    public String onLevel2Data(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        if (callback != null) {
//            this.listener.setLevel2Callback(callback);
//        }
//        String topic = APIConstants.API_LEVEL2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
//        return subscribe(topic, false, true);
//    }
//
//    @Override
//    public String onLevel2Data(int depth, CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        String topic = null;
//        String market = Arrays.stream(symbols).collect(Collectors.joining(","));
//        if (depth == 5) {
//            if (callback != null) {
//                this.listener.setLevel2Depth5Callback(callback);
//            }
//            topic = APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX + market;
//        } else if (depth == 50) {
//            if (callback != null) {
//                this.listener.setLevel2Depth50Callback(callback);
//            }
//            topic = APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX + market;
//        }
//        if (topic == null) {
//            return null;
//        }
//
//        return subscribe(topic, false, true);
//    }
//
//    @Override
//    public String onMatchExecutionData(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        String market = Arrays.stream(symbols).collect(Collectors.joining(","));
//        if (callback != null) {
//            this.listener.setMatchDataCallback(callback);
//        }
//        String topic = APIConstants.API_MATCH_TOPIC_PREFIX + market;
//        return subscribe(topic, false, true);
//    }
//
//    @Override
//    public String onLevel3Data_V2(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        if (callback != null) {
//            this.listener.setLevel3V2Callback(callback);
//        }
//        String topic = APIConstants.API_LEVEL3_V2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
//        return subscribe(topic, false, true);
//    }
//
//    @Override
//    @Deprecated
//    public String onLevel3Data(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        if (callback != null) {
//            this.listener.setLevel3Callback(callback);
//        }
//        String topic = APIConstants.API_LEVEL3_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
//        return subscribe(topic, false, true);
//    }
//
//    @Override
//    public String ping(String requestId) {
//        return super.ping(requestId);
//    }
//
//    @Override
//    public String unsubscribe(PublicChannelEnum channelEnum, String... symbols) {
//        return super.unsubscribe(channelEnum.getTopicPrefix() + Arrays.stream(symbols).collect(Collectors.joining(",")),
//                false, true);
//    }
//
//    @Override
//    public String onSnapshot(CoinexAPICallback<KucoinEvent> callback, String target) {
//        if (callback != null) {
//            this.listener.setSnapshotCallback(callback);
//        }
//        String topic = APIConstants.API_SNAPSHOT_PREFIX + target;
//        return subscribe(topic, false, true);
//    }
//
//
//}
