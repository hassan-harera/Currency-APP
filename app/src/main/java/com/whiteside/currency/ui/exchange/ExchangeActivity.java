package com.whiteside.currency.ui.exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.CountryCurrencyPicker;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import com.scrounger.countrycurrencypicker.library.PickerType;
import com.whiteside.currency.R;
import com.whiteside.currency.model.ExchangeData;
import com.whiteside.currency.ui.exchange.CurrencyExchangeViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeActivity extends AppCompatActivity {

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
    @BindView(R.id.amount)
    EditText amount;

    private CurrencyExchangeViewModel currencyExchangeViewModel;
    private String currency1 = "USD", currency2 = "EGP";
    private float amountInt = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        ButterKnife.bind(this);

        currencyExchangeViewModel = ViewModelProviders.of(this).get(CurrencyExchangeViewModel.class);
        currencyExchangeViewModel.mutableLiveData.observe(this, new Observer<ExchangeData>() {
            @Override
            public void onChanged(ExchangeData response) {
//                result.setText(String.valueOf(amountInt + " " + currency1 + " = " + response.getResult() + " " +  currency2));
                result.setText(response.getText());
            }
        });

        currencyExchangeViewModel.getExchangeResult(amountInt, currency1, currency2);

        setAmountListener();
    }

    private void setAmountListener() {
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                amountInt = Float.parseFloat(amount.getText().toString());
                currencyExchangeViewModel.getExchangeResult(amountInt, currency1, currency2);
            }
        });
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
                        currencyExchangeViewModel.getExchangeResult(amountInt, currency1, currency2);
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
                        currencyExchangeViewModel.getExchangeResult(amountInt, currency1, currency2);
                    }
                });

        picker.show(getSupportFragmentManager(), CountryCurrencyPicker.DIALOG_NAME);
    }
}