//package com.coinex.sdk.socket;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.coinex.sdk.constants.APIConstants;
//import com.coinex.sdk.websocket.event.*;
//import com.coinex.sdk.websocket.CoinexAPICallback;
//import com.coinex.sdk.websocket.PrintCallback;
//import com.coinex.sdk.websocket.event.*;
//import com.coinex.sdk.model.enums.PrivateChannelEnum;
//import com.coinex.sdk.rest.response.WebsocketTokenResponse;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.log4j.Log4j2;
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.handshake.ServerHandshake;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.*;
//
///**
// * Created By Alireza Dolatabadi
// * Date: 4/30/2023
// * Time: 7:55 PM
// */
//@Log4j2
//@Setter
//@Getter
//public class PrivateClient extends BaseClient {
//    private final WebsocketPrivateAPIAdaptor websocketPrivateAPIAdaptor;
//    private WebSocketClient webSocketClient;
//    private boolean isConnected = false;
//    private Long pingInterval;
//
//    private CoinexAPICallback<KucoinEvent<OrderActivateEvent>> orderActivateCallback = new PrintCallback<>();
//    private CoinexAPICallback<KucoinEvent<AccountChangeEvent>> accountChangeCallback = new PrintCallback<>();
//    private CoinexAPICallback<KucoinEvent<OrderChangeEvent>> orderChangeCallback = new PrintCallback<>();
//    private CoinexAPICallback<KucoinEvent<? extends AdvancedOrderEvent>> advancedOrderCallback = new PrintCallback<>();
//
//    private Map<PrivateChannelEnum, Set<String>> usages = new HashMap<>();
//
//
//    public PrivateClient(WebsocketPrivateAPIAdaptor websocketPrivateAPIAdaptor) throws URISyntaxException, IOException {
//        this.websocketPrivateAPIAdaptor = websocketPrivateAPIAdaptor;
//        if (this.webSocketClient != null) {
//            return;
//        }
//        WebsocketTokenResponse privateToken = websocketPrivateAPIAdaptor.getPrivateToken();
//        this.webSocketClient = createNewWebsocket(
//                privateToken.getInstanceServers().get(0).getEndpoint() +
//                        "?token=" + privateToken.getToken(),
//                privateToken.getInstanceServers().get(0).getPingInterval()
//        );
//
//            webSocketClient.connect();
//
//    }
//
//    private WebSocketClient createNewWebsocket(String url, long pingInterval) throws URISyntaxException {
//        return new WebSocketClient(new URI(url)) {
//            public void onOpen(ServerHandshake serverHandshake) {
//                log.debug("connected");
//                usages.forEach((k, v) -> {
//                    switch (k) {
//                        case ORDER_CHANGE:
//                            onOrderChange(orderChangeCallback, String.valueOf(v));
//                            break;
//                        case ACCOUNT:
//                            onAccountBalance(accountChangeCallback);
//                            break;
//                        case ADVANCED_ORDER:
//                            onAdvancedOrder(advancedOrderCallback, String.valueOf(v));
//                            break;
//                    }
//                });
//                new Thread(() -> {
//                    while (true) {
//                        try {
//                            Thread.sleep(pingInterval);
//                            ping(UUID.randomUUID().toString());
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (Exception ignored) {
//                            try {
//                                reconnectSocket();
//                                Thread.sleep(60000);
//                                break;
//                            }  catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }).start();
//            }
//
//
//            public void onMessage(String text) {
//                isConnected = true;
//                log.debug("Got message: {}", text);
//                JsonNode jsonObject = tree(text);
//                log.debug("Parsed message OK");
//
//                String type = jsonObject.get("type").asText();
//                if (!type.equals("message")) {
//                    log.debug("Ignoring message type ({})", type);
//                    return;
//                }
//
//                String topic = jsonObject.get("topic").asText();
//                if (topic.contains(APIConstants.API_ACTIVATE_TOPIC_PREFIX.getValue())) {
//                    KucoinEvent<OrderActivateEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<OrderActivateEvent>>() {
//                    });
//                    orderActivateCallback.onResponse(kucoinEvent);
//                } else if (topic.contains(APIConstants.API_BALANCE_TOPIC_PREFIX.getValue())) {
//                    KucoinEvent<AccountChangeEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<AccountChangeEvent>>() {
//                    });
//                    accountChangeCallback.onResponse(kucoinEvent);
//                } else if (topic.contains(APIConstants.API_ORDER_TOPIC_PREFIX.getValue())) {
//                    KucoinEvent<OrderChangeEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<OrderChangeEvent>>() {
//                    });
//                    orderChangeCallback.onResponse(kucoinEvent);
//                } else if (topic.contains(APIConstants.API_ADVANCED_ORDER_TOPIC_PREFIX.getValue())) {
//                    String subject = jsonObject.get("subject").asText();
//                    KucoinEvent<? extends AdvancedOrderEvent> kucoinEvent = null;
//                    if (Objects.equals(subject, "stopOrder")) {
//                        kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<StopOrderEvent>>() {
//                        });
//                    }
//                    if (kucoinEvent != null) {
//                        advancedOrderCallback.onResponse(kucoinEvent);
//                    }
//                }
//            }
//
//
//            public void onClose(int i, String s, boolean b) {
//                log.error("closed");
//                isConnected = false;
//                System.err.println(s);
//
//            }
//
//
//            public void onError(Exception e) {
//                log.error("error");
//                log.error(e.getMessage());
//                isConnected = false;
//
//            }
//
//
//            @Override
//            public void reconnect() {
//                super.reconnect();
//
//            }
//        };
//    }
//
//    private void reconnectSocket() throws URISyntaxException, IOException {
//        webSocketClient.close();
//        WebsocketTokenResponse privateToken = websocketPrivateAPIAdaptor.getPrivateToken();
//        webSocketClient = createNewWebsocket(
//                privateToken.getInstanceServers().get(0).getEndpoint() +
//                        "?token=" + privateToken.getToken(),
//                privateToken.getInstanceServers().get(0).getPingInterval()
//        );
//        webSocketClient.connect();
//    }
//
//
//    public String onAccountBalance(CoinexAPICallback<KucoinEvent<AccountChangeEvent>> callback) {
//        if (callback != null) {
//            this.setAccountChangeCallback(callback);
//        }
//        usages.put(PrivateChannelEnum.ACCOUNT, null);
//        return subscribe(APIConstants.API_BALANCE_TOPIC_PREFIX.getValue(), true, true, webSocketClient, null);
//    }
//
//    public String onOrderChange(CoinexAPICallback<KucoinEvent<OrderChangeEvent>> callback, String... symbols) {
//        if (callback != null) {
//            this.setOrderChangeCallback(callback);
//        }
//        usages.put(PrivateChannelEnum.ORDER_CHANGE, new HashSet<>(Arrays.asList(symbols)));
//        return subscribe(APIConstants.API_ORDER_TOPIC_PREFIX.getValue(), true, true, webSocketClient, null);
//    }
//
//
//    public String onAdvancedOrder(CoinexAPICallback<KucoinEvent<? extends AdvancedOrderEvent>> callback, String... symbols) {
//        if (callback != null) {
//            this.setAdvancedOrderCallback(callback);
//        }
//        usages.put(PrivateChannelEnum.ADVANCED_ORDER, new HashSet<>(Arrays.asList(symbols)));
//        return subscribe(APIConstants.API_ADVANCED_ORDER_TOPIC_PREFIX.getValue(), true, true, webSocketClient, null);
//    }
//
//
//    public String ping(String requestId) {
//        return super.ping(requestId, webSocketClient);
//    }
//
//
//    public String unsubscribe(PrivateChannelEnum channelEnum, String... symbols) {
//        return super.unsubscribe(channelEnum.getTopicPrefix() + String.join(",", symbols), true, true, webSocketClient);
//    }
//
//    public void reconnect() {
//        webSocketClient.reconnect();
//    }
//}
