package com.whiteside.currency.ui.enquiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.CountryCurrencyPicker;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import com.scrounger.countrycurrencypicker.library.PickerType;
import com.whiteside.currency.R;
import com.whiteside.currency.model.EnquiryData;
import com.whiteside.currency.ui.enquiry.CurrencyEnquiryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnquiryActivity extends AppCompatActivity {

    @BindView(R.id.currency_1)
    TextView currency_1;
    @BindView(R.id.currency_2)
    TextView currency_2;
    @BindView(R.id.flag_1)
    ImageView flag_1;
    @BindView(R.id.flag_2)
    ImageView flag_2;
    @BindView(R.id.result)
    TextView result;

    private CurrencyEnquiryViewModel currencyEnquiryViewModel;
    private String currency1 = "USD", currency2 = "EGP";
    private static final String TAG = "EnquiryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);

        ButterKnife.bind(this);

        currencyEnquiryViewModel = ViewModelProviders.of(this).get(CurrencyEnquiryViewModel.class);
        currencyEnquiryViewModel.mutableLiveData.observe(this, new Observer<EnquiryData>() {
            @Override
            public void onChanged(EnquiryData response) {
                result.setText(String.valueOf("1 " + response.getSource() + " = " + response.getRates().get(currency2) + " " + currency2));
            }
        });

        currencyEnquiryViewModel.getEnquiryResult(currency1, currency2);
    }

    public void firstCurrencyClicked(View view) {
        CountryCurrencyPicker picker;
        picker = CountryCurrencyPicker
                .newInstance(PickerType.CURRENCY, new CountryCurrencyPickerListener() {
                    @Override
                    public void onSelectCountry(Country country) {
                    }

                    @Override
                    public void onSelectCurrency(Currency currency) {
                        flag_1.setImageResource(currency.getFlagId());
                        currency_1.setText(currency.getCode());
                        currency1 = currency.getCode();
                        currencyEnquiryViewModel.getEnquiryResult(currency1, currency2);
                    }
                });

        picker.show(getSupportFragmentManager(), CountryCurrencyPicker.DIALOG_NAME);
    }

    public void secondCurrencyClicked(View view) {
        CountryCurrencyPicker picker;
        picker = CountryCurrencyPicker
                .newInstance(PickerType.CURRENCY, new CountryCurrencyPickerListener() {
                    @Override
                    public void onSelectCountry(Country country) {
                    }

                    @Override
                    public void onSelectCurrency(Currency currency) {
                        flag_2.setImageResource(currency.getFlagId());
                        currency_2.setText(currency.getCode());
                        currency2 = (currency.getCode());
                        currencyEnquiryViewModel.getEnquiryResult(currency1, currency2);
                    }
                });

        picker.show(getSupportFragmentManager(), CountryCurrencyPicker.DIALOG_NAME);
    }
}