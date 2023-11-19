package com.coinex.sdk.model;

import lombok.Data;

/**
 * Created By Alireza Dolatabatdi
 * Date: 9/18/2023
 * Time: 12:03 PM
 */

@Data
public class MarketInfo {

    private String name;
    private String min_amount;
    private String maker_fee_rate;
    private String taker_fee_rate;
    private String pricing_name;
}
