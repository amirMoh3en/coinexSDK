package com.coinex.sdk.rest.interfaces;

import com.coinex.sdk.rest.request.OrderCreateApiRequest;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:02 PM
 */
public interface OrderAPI {

    LinkedHashMap createMarketOrder(
            String secret ,
            OrderCreateApiRequest opsRequest) throws IOException;

}
