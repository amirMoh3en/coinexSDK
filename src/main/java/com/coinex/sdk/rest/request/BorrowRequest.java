/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.coinex.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BorrowRequest {

    private String currency;

    private BigDecimal size;

    private BigDecimal maxRate;

    private String term;

    private String type;
}
