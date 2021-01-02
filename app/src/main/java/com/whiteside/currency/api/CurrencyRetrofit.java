package com.whiteside.currency.api;

import com.whiteside.currency.model.EnquiryData;
import com.whiteside.currency.model.ExchangeData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRetrofit {

    private static final String URL_BASE = "https://v1.nocodeapi.com/harera/cx/EtAoqrMszLbcpNpC/";
    private static CurrencyRetrofit INSTANCE;
    private static CurrencyAPI currencyAPI;


    private CurrencyRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        currencyAPI = retrofit.create(CurrencyAPI.class);
    }

    public static CurrencyRetrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CurrencyRetrofit();
        }
        return INSTANCE;
    }

    public Call<EnquiryData> getCurrencyEnquiry(String from, String to) {
        return currencyAPI.getCurrencyEnquiry(from, to);
    }


    public Call<ExchangeData> getCurrencyExchange(float amount, String from, String to) {
        return currencyAPI.getCurrencyExchange(amount, from, to);
    }

    public Call<EnquiryData> getCurrencyEnquiry(String apiKey) {
        return currencyAPI.getCurrencyEnquiry(apiKey);
    }
}
