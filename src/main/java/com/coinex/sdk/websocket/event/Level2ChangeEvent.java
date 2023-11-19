/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.websocket.event;

import lombok.Data;
import stellar.sdk.OrderBook;

/**
 * Created by chenshiwei on 2019/1/11.
 */
@Data
public class Level2ChangeEvent {

    private long sequenceStart;

    private long sequenceEnd;

    private String symbol;

    private OrderBook changes;

}
