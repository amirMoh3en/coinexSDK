package com.coinex.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class CoinexObjectMapper {

    public static final ObjectMapper INSTANCE;
  
    static {
        INSTANCE = new ObjectMapper();
        INSTANCE.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}