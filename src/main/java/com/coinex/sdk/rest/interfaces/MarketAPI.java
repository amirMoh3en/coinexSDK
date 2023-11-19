package com.coinex.sdk.rest.interfaces;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:02 PM
 */
public interface MarketAPI {

    LinkedHashMap getAllmarketInfo() throws IOException;

    List<List<Object>> getKlineMarket(String market , int limit , String type) throws IOException;

    List<String> getAllMarkets() throws IOException;
}
