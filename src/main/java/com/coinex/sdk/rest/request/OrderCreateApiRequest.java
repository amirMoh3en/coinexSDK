/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderCreateApiRequest {


    private final String access_id;

    /**
     * buy or sell
     */
    private final String type;

    /**
     * price per base currency
     */
    private final String market;

    /**
     * amount of base currency to buy or sell
     */
    private final Double amount;

    /**
     * [optional] Desired amount of quote currency to use
     */
    private final Double price;


    private final String tonce;

}