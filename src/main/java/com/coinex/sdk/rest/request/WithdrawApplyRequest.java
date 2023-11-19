/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zicong.lu on 2018/12/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Builder
@Setter
public class WithdrawApplyRequest {

    /**
     * Currency
     */
    private String coin_type;

    /**
     * Withdrawal address
     */
    private String coin_address;

    /**
     * Withdrawal amount, a multiple and positive number of the amount precision (fees excluded)
     */
    private Double actual_amount;


    /**
     * [optional] Remark
     */
    private String tonce;

    private String access_id;

    private String  smart_contract_name;
}
