package com.example.thomb.scanner.AddingActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.thomb.scanner.LayoutControllers.ScanOrSkuController;
import com.example.thomb.scanner.R;

public class AddStockBinVerifiedActivity extends AppCompatActivity {

    private ScanOrSkuController scanOrSkuController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_bin_verified);
        scanOrSkuController = new ScanOrSkuController();
        scanOrSkuController.setListeners();



    }
}
