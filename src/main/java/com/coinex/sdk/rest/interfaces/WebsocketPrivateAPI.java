/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.interfaces;

import com.coinex.sdk.rest.response.WebsocketTokenResponse;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public interface WebsocketPrivateAPI {

    /**
     * apply for a private token to create a websocket connection
     *
     * @return A new private API token.
     */
    WebsocketTokenResponse getPrivateToken() throws IOException;

}
