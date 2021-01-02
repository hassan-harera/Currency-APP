package com.whiteside.currency.ui.enquiry;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whiteside.currency.model.EnquiryData;
import com.whiteside.currency.api.CurrencyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyEnquiryViewModel extends ViewModel {

    MutableLiveData<EnquiryData> mutableLiveData = new MutableLiveData<>();
    private CurrencyRetrofit currencyRetrofit;

    public void getEnquiryResult(String from, String to) {
        currencyRetrofit = CurrencyRetrofit.getInstance();
        currencyRetrofit.getCurrencyEnquiry(from, to).enqueue(new Callback<EnquiryData>() {
            @Override
            public void onResponse(Call<EnquiryData> call, Response<EnquiryData> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<EnquiryData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getEnquiryResult(String s) {
        currencyRetrofit = CurrencyRetrofit.getInstance();
        currencyRetrofit.getCurrencyEnquiry(s).enqueue(new Callback<EnquiryData>() {
            @Override
            public void onResponse(Call<EnquiryData> call, Response<EnquiryData> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<EnquiryData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
