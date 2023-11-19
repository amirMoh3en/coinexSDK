/**
 * Copyright 2019 Mek Global Limited.
 */

package com.coinex.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zicong.lu on 2018/12/14.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinexResponse<R> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS_CODE = "0";
    protected String code;
    protected String message;

    private R data;

    public boolean isSuccessful() {
        return SUCCESS_CODE.equals(this.code);
    }

}
