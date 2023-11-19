package com.coinex.sdk.socket;

import com.coinex.sdk.CoinexObjectMapper;
import com.coinex.sdk.websocket.event.KucoinEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.java_websocket.client.WebSocketClient;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created By Alireza Dolatabadi
 * Date: 4/30/2023
 * Time: 7:55 PM
 */
public class BaseClient {
    protected JsonNode tree(String text) {
        try {
            return CoinexObjectMapper.INSTANCE.readTree(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }

    protected <T> T deserialize(String text, TypeReference<T> typeReference) {
        try {
            return CoinexObjectMapper.INSTANCE.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }

    protected String serialize(Object o) {
        try {
            return CoinexObjectMapper.INSTANCE.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failure serializing object", e);
        }
    }


    protected String subscribe(String method,
                               Set<Object> params,
                               WebSocketClient webSocketClient) {
        int id = new Random(Integer.MAX_VALUE).nextInt();
        KucoinEvent subscribe = new KucoinEvent();
        subscribe.setId(id);
        subscribe.setMethod(method);
        subscribe.setParams(params);
        webSocketClient.send(serialize(subscribe));
        return null;
    }

    public int ping(WebSocketClient webSocketClient) {
        int id = new Random(Integer.MAX_VALUE).nextInt();
        KucoinEvent ping = new KucoinEvent();
        ping.setId(id);
        ping.setMethod("server.ping");
        ping.setParams(new HashSet<>());
        webSocketClient.send(serialize(ping));
        return id;
    }
}
