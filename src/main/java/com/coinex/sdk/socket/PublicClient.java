package com.coinex.sdk.socket;


import com.coinex.sdk.constants.APIConstants;
import com.coinex.sdk.model.enums.PublicChannelEnum;
import com.coinex.sdk.websocket.CoinexAPICallback;
import com.coinex.sdk.websocket.PrintCallback;
import com.coinex.sdk.websocket.event.KucoinEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created By Alireza Dolatabadi
 * Date: 4/30/2023
 * Time: 4:14 PM
 */
@Log4j2
@Setter
@Getter
public class PublicClient extends com.coinex.sdk.socket.BaseClient {
    Map<String, String> tunnelIds = new HashMap<>(5);
    private WebSocketClient webSocketClient;
    private Long pingInterval;
    private boolean isConnected = false;
    private final String url;
    private CoinexAPICallback<KucoinEvent> tickerCallback = new PrintCallback<>();

    private Map<PublicChannelEnum, Set<Object>> usages = new HashMap<PublicChannelEnum, Set<Object>>();

    public PublicClient(String url) throws URISyntaxException, IOException {
        this.url = url;
        this.webSocketClient = createWebSocket(url,
                10L);

        webSocketClient.connect();
    }

    private WebSocketClient createWebSocket(String url, Long pingInterval) throws URISyntaxException {
        return new WebSocketClient(new URI(url)) {
            public void onOpen(ServerHandshake serverHandshake) {
                log.debug("connected");
                usages.forEach((k, v) -> {
                    switch (k) {
                        case TICKER:
                            onTicker(tickerCallback,  v);
                            break;
//                        case LEVEL2:
//                            onLevel2Data(level2Callback, String.valueOf(v));
//                            break;
//                        case LEVEL2_DEPTH5:
//                            onLevel2Data(5, level2Depth5Callback, String.valueOf(v));
//                            break;
//                        case LEVEL2_DEPTH50:
//                            onLevel2Data(50, level2Depth50Callback, String.valueOf(v));
//                            break;
//                        case MATCH:
//                            onMatchExecutionData(matchDataCallback, String.valueOf(v));
//                            break;
//                        case LEVEL3:
//                            onLevel3Data(level3Callback, String.valueOf(v));
//                            break;
//                        case LEVEL3_V2:
//                            onLevel3Data_V2(level3V2Callback, String.valueOf(v));
//                            break;
//                        case SNAPSHOT:
//                            onSnapshot(snapshotCallback, v, null);
//                            break;
                    }
                });
                new Thread(() -> {
                    Thread.currentThread().setName("Public Checker");
                    while (true) {
                        try {
                            Thread.sleep(pingInterval);
                            ping();
                        } catch (Exception ex) {
                            System.err.println("Before Reconnect");
//                            ex.printStackTrace();
                            try {
                                System.err.println("In Reconnect");
                                reconnectSocket();
                                Thread.sleep(60000);
                                break;
                            } catch (Exception e) {
                                System.err.println("In Reconnect 2");
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }


            public void onMessage(String text) {
                isConnected = true;
                log.debug("Got message: {}", text);
                JsonNode jsonObject = tree(text);
                log.debug("Parsed message OK");
                String type = jsonObject.get("error") != null ? jsonObject.get("error").asText() : null;
                if (type != null) {
                    log.debug("Ignoring message type ({})", type);
                    String result = jsonObject.get("result").asText();
                    if (!result.equals("pong")) {
//                        System.err.println(text);
                    }
                    return;
                }

                String topic = jsonObject.get("method").asText();
                if (topic.contains(APIConstants.API_TICKER_TOPIC_PREFIX_RESPONSE.getValue())) {
                    KucoinEvent kucoinEvent = deserialize(text, new TypeReference<KucoinEvent>() {
                    });
                    tickerCallback.onResponse(kucoinEvent);
                }
            }


            public void onClose(int i, String s, boolean b) {
                log.error("closed");
                isConnected = false;
                System.err.println(s);

            }


            public void onError(Exception e) {
                log.error("error");
                log.error(e.getMessage());
                isConnected = false;
                e.printStackTrace();


            }

        };
    }

    public synchronized WebSocketClient getWebSocketClient() {
        if (webSocketClient == null) {
            throw new RuntimeException("NO_SOCKET");
        }
        return webSocketClient;
    }


    public String onTicker(CoinexAPICallback<KucoinEvent> callback,
                           Set<Object> symbols) {
        if (callback != null) {
            this.setTickerCallback(callback);
        }
        usages.put(PublicChannelEnum.TICKER, symbols);
        String topic = APIConstants.API_TICKER_TOPIC_PREFIX.getValue();
        return subscribe(topic, symbols , webSocketClient);
    }


//    public String onLevel2Data(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        if (callback != null) {
//            this.setLevel2Callback(callback);
//        }
//        usages.put(PublicChannelEnum.LEVEL2, new HashSet<>(Arrays.asList(symbols)));
//        String topic = APIConstants.API_LEVEL2_TOPIC_PREFIX.getValue() + Arrays.stream(symbols).collect(Collectors.joining(","));
//        return subscribe(topic, true, false, webSocketClient, null);
//    }
//
//
//    public String onLevel2Data(int depth, CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        String topic = null;
//        String market = Arrays.stream(symbols).collect(Collectors.joining(","));
//        if (depth == 5) {
//            if (callback != null) {
//                this.setLevel2Depth5Callback(callback);
//            }
//            topic = APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX.getValue() + market;
//        } else if (depth == 50) {
//            if (callback != null) {
//                this.setLevel2Depth50Callback(callback);
//            }
//            topic = APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX.getValue() + market;
//        }
//        if (topic == null) {
//            return null;
//        }
//        usages.put(PublicChannelEnum.LEVEL2_DEPTH50, new HashSet<>(Arrays.asList(symbols)));
//
//        return subscribe(topic, true, false, webSocketClient, null);
//    }
//
//
//    public String onMatchExecutionData(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        String market = Arrays.stream(symbols).collect(Collectors.joining(","));
//        if (callback != null) {
//            this.setMatchDataCallback(callback);
//        }
//        usages.put(PublicChannelEnum.MATCH, new HashSet<>(Arrays.asList(symbols)));
//        String topic = APIConstants.API_MATCH_TOPIC_PREFIX.getValue() + market;
//        return subscribe(topic, true, false, webSocketClient, null);
//    }
//
//
//    public String onLevel3Data_V2(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        if (callback != null) {
//            this.setLevel3V2Callback(callback);
//        }
//        usages.put(PublicChannelEnum.LEVEL3_V2, new HashSet<>(Arrays.asList(symbols)));
//        String topic = APIConstants.API_LEVEL3_V2_TOPIC_PREFIX.getValue() + Arrays.stream(symbols).collect(Collectors.joining(","));
//        return subscribe(topic, true, false, webSocketClient, null);
//    }
//
//
//    @Deprecated
//    public String onLevel3Data(CoinexAPICallback<KucoinEvent> callback, String... symbols) {
//        if (callback != null) {
//            this.setLevel3Callback(callback);
//        }
//        usages.put(PublicChannelEnum.LEVEL3, new HashSet<>(Arrays.asList(symbols)));
//        String topic = APIConstants.API_LEVEL3_TOPIC_PREFIX.getValue() +
//                String.join(",", symbols);
//        return subscribe(topic, true, false, webSocketClient, null);
//    }
//
    public int ping() {
        return super.ping(webSocketClient);
    }
//
//    public String unsubscribe(PublicChannelEnum channelEnum, String... symbols) {
//        usages.remove(channelEnum);
//        return unsubscribe(channelEnum.getTopicPrefix() +
//                        String.join(",", symbols),
//                false, true, webSocketClient);
//    }
//
//    public void onSnapshot(
//            CoinexAPICallback<KucoinEvent> callback,
//            Set<String> marketsString,
//            String bt1) {
//        if (usages.get(PublicChannelEnum.SNAPSHOT) != null) {
//            usages.get(PublicChannelEnum.SNAPSHOT).addAll(marketsString);
//        } else {
//            usages.put(PublicChannelEnum.SNAPSHOT, marketsString);
//        }
//        if (callback != null) {
//            this.setSnapshotCallback(callback);
//        }
//
//        Iterable<List<String>> smallerLists = Iterables.partition(marketsString, 100);
//        smallerLists.forEach(strings -> {
//            String symbols = "";
//            for (String market : strings) {
//                symbols = symbols.concat(market + ",");
//            }
//            symbols = symbols.substring(0, symbols.length() - 1);
//            String topic = APIConstants.API_SNAPSHOT_PREFIX.getValue() + symbols;
//            subscribe(topic, true, false, webSocketClient, null);
//        });
//    }
//
//
    public void reconnectSocket() throws IOException, URISyntaxException {
        webSocketClient.close();
        webSocketClient = createWebSocket(url,
                10L);
        webSocketClient.connect();
    }
//
//
//    public void createTunnel(String tunnel, String usage) {
//        String uuid = UUID.randomUUID().toString();
//        KucoinEvent subscribe = new KucoinEvent();
//        subscribe.setId(uuid);
//        subscribe.setType("openTunnel");
//        subscribe.setNewTunnelId(tunnel);
//        subscribe.setResponse(true);
//        webSocketClient.send(serialize(subscribe));
//        tunnelIds.put(tunnel, usage);
//    }
}
