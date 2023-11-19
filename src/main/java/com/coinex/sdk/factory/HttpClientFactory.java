/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.factory;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class HttpClientFactory {
    private static Dispatcher dispatcher = new Dispatcher();
    private static List<OkHttpClient> privateClient = new ArrayList<>();
    private static List<OkHttpClient> publicClient = new ArrayList<>();

    public static OkHttpClient getPublicClient() {
        OkHttpClient okHttpClient = buildHttpClient();
        publicClient.add(okHttpClient);
        return okHttpClient;
    }

    public static OkHttpClient getPrivateClient() {
        OkHttpClient okHttpClient = buildHttpClient();
        privateClient.add(okHttpClient);
        return okHttpClient;
    }

    public static OkHttpClient getAuthClient() {
        return buildHttpClient();
    }

    private static OkHttpClient buildHttpClient() {
        dispatcher.setMaxRequestsPerHost(100);
        dispatcher.setMaxRequests(100);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .pingInterval(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(
                        new ConnectionPool(
                                10 ,
                                5 ,
                                TimeUnit.SECONDS)
                )
                .dispatcher(dispatcher);
        return builder.build();
    }

    public static void stopClient() {
        privateClient.forEach(c -> {
            try {
                c.cache().close();
            } catch (Exception e) {
            }
            c.dispatcher().executorService().shutdown();
            c.connectionPool().evictAll();
        });
        publicClient.forEach(c -> {
            try {
                c.cache().close();
            } catch (Exception e) {
            }
            c.dispatcher().executorService().shutdown();
            c.connectionPool().evictAll();
        });
        dispatcher.executorService().shutdownNow();
        dispatcher.cancelAll();
        publicClient = new ArrayList<>();
        privateClient = new ArrayList<>();
        System.gc();
        dispatcher = new Dispatcher();
    }

}
