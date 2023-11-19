package com.coinex.sdk.model;

import lombok.Data;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 1:07 PM
 */
@Data
public class CoinInfo {
    private String asset;
    private String chain;
    private String deposit_least_amount;
    private String withdraw_least_amount;
    private String withdraw_tx_fee;
    private Boolean can_deposit;
    private Boolean can_withdraw;
}