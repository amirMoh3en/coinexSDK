/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.exception;

/**
 * Created by zicong.lu on 2018/12/14.
 */
public class CoinexApiException extends RuntimeException {

    private static final long serialVersionUID = 8592824166988095909L;

    private String code;

    public CoinexApiException(String message) {
        super(message);
    }

    public CoinexApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoinexApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "CoinexApiException{" +
                "code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
