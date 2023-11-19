/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.response;

import com.coinex.sdk.model.InstanceServer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/15.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebsocketTokenResponse {

    private List<InstanceServer> instanceServers;

    private String token;

}
