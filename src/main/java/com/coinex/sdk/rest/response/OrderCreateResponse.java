/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreateResponse {

    private String orderId;
}
