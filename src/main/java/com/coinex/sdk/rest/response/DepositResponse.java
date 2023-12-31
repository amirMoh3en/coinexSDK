/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.response;

import com.coinex.sdk.model.enums.DepositStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositResponse {

    private String currency;

    private DepositStatusEnum status;

    private String address;

    private String memo;

    @JsonProperty("isInner")
    private Boolean isInner;

    private BigDecimal amount;

    private BigDecimal fee;

    private String walletTxId;

    private Long createdAt;

    private Long updatedAt;

    private String remark;
}
