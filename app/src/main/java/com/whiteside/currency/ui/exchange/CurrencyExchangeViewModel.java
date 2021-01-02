package com.whiteside.currency.ui.exchange;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whiteside.currency.model.ExchangeData;
import com.whiteside.currency.api.CurrencyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyExchangeViewModel extends ViewModel {

    MutableLiveData<ExchangeData> mutableLiveData = new MutableLiveData<>();
    private CurrencyRetrofit currencyRetrofit;

    public void getExchangeResult(float amount, String from, String to) {
        currencyRetrofit = CurrencyRetrofit.getInstance();
        currencyRetrofit.getCurrencyExchange(amount, from, to).enqueue(new Callback<ExchangeData>() {
            @Override
            public void onResponse(Call<ExchangeData> call, Response<ExchangeData> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ExchangeData> call, Throwable t) {

            }
        });
    }
}
