/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.websocket.event;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
@ToString
public class KucoinEvent {

    private int id;

    private String method;

    private Set<Object> params;

}
