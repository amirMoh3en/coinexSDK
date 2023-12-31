/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerResponse {

    private String sequence;

    private BigDecimal bestAsk;

    private BigDecimal bestBid;

    private BigDecimal size;

    private BigDecimal price;

    private BigDecimal bestAskSize;

    private BigDecimal bestBidSize;

    private long time;

}
