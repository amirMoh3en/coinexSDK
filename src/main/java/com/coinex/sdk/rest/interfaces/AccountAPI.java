package com.coinex.sdk.rest.interfaces;

import com.coinex.sdk.rest.request.WithdrawApplyRequest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created By Alireza Dolatabadi
 * Date: 9/18/2023
 * Time: 12:02 PM
 */
public interface AccountAPI {

    List<LinkedHashMap> getWithdrawalHistory(
            String secret,
            String access_id,
            String tonce,
            Integer page, Integer limit
    ) throws IOException;

    LinkedHashMap getBalance(
            String secret,
            String access_id,
            String tonce
    ) throws IOException;

    LinkedHashMap applyWithdraw(String secret, WithdrawApplyRequest build)
            throws IOException;
}
