package com.whiteside.currency.api;

import com.whiteside.currency.model.EnquiryData;
import com.whiteside.currency.model.ExchangeData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyAPI {

    @GET("rates/")
    Call<EnquiryData> getCurrencyEnquiry(@Query("source") String source, @Query("target") String target);

    @GET("/live")
    Call<EnquiryData> getCurrencyEnquiry(@Query("access_key") String source);

    @GET("rates/convert/")
    Call<ExchangeData> getCurrencyExchange(@Query("amount") float amount,
                                           @Query("from") String from,
                                           @Query("to") String to);
}
