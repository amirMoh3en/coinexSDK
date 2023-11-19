package com.coinex.sdk.rest.interfaces;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:02 PM
 */
public interface CurrencyAPI {

    LinkedHashMap getCoinDetails() throws IOException;

    LinkedHashMap getCoinDetails(String coinCode) throws IOException;
}
