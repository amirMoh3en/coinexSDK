/**
 * Copyright 2019 Mek Global Limited.
 */
package com.coinex.sdk.rest.impl.retrofit;

import com.coinex.sdk.CoinexObjectMapper;
import com.coinex.sdk.exception.CoinexApiException;
import com.coinex.sdk.rest.response.CoinexResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public abstract class AbstractRetrofitAPIImpl<T> {

    private static final Converter.Factory jacksonConverterFactory = JacksonConverterFactory.create(CoinexObjectMapper.INSTANCE);

    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, CoinexResponse<?>> errorBodyConverter =
            (Converter<ResponseBody, CoinexResponse<?>>) jacksonConverterFactory.responseBodyConverter(
                    CoinexResponse.class, new Annotation[0], null);

    protected String baseUrl;

    protected String secret;

    public abstract T getAPIImpl();

    /**
     * Execute a REST call and block until the response is received.
     *
     * @throws IOException On socket related errors.
     */
    public <R> R executeSync(Call<CoinexResponse<R>> call) throws IOException {
        Response<CoinexResponse<R>> response = call.execute();
        CoinexResponse<?> errorResponse = null;
        try {
            if (response.isSuccessful() && response.body() != null && (response.body().isSuccessful() || call.request().url().toString().contains("bullet-public"))) {
                return response.body().getData();
            } else {
                if (response.isSuccessful()) {
                    errorResponse = response.body();
                } else {
                    errorResponse = getErrorResponse(response);
                }
            }
        } catch (UnknownHostException | NoRouteToHostException er) {
//            CoinexThread.setFailure(true);
        }
        throw new CoinexApiException(errorResponse.getCode(), errorResponse.getMessage());
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public CoinexResponse<?> getErrorResponse(Response<?> response) throws IOException, CoinexApiException {
        return errorBodyConverter.convert(response.errorBody());
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
