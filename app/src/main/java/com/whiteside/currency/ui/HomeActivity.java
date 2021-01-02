package com.whiteside.currency.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.whiteside.currency.R;
import com.whiteside.currency.ui.enquiry.EnquiryActivity;
import com.whiteside.currency.ui.exchange.ExchangeActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void enquiryClicked(View view) {
        Intent intent = new Intent(this, EnquiryActivity.class);
        startActivity(intent);
    }

    public void exchangeClicked(View view) {
        Intent intent = new Intent(this, ExchangeActivity.class);
        startActivity(intent);
    }

}