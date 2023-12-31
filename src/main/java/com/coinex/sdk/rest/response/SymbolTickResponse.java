/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yi.yang
 * @since 2018/12/26.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolTickResponse {

    private String symbol;

    private BigDecimal changeRate;

    private BigDecimal changePrice;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal vol;

    private BigDecimal volValue;

    private BigDecimal last;

    private BigDecimal buy;

    private BigDecimal sell;

    private BigDecimal averagePrice;

    private BigDecimal takerFeeRate;

    private BigDecimal makerFeeRate;

    private BigDecimal takerCoefficient;

    private BigDecimal makerCoefficient;

    private long time;

}
