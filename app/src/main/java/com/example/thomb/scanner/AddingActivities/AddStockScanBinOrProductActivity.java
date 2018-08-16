package com.example.thomb.scanner.AddingActivities;
import android.content.Intent;
import android.support.constraint.solver.widgets.Helper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.Helpers.GlobalAccessor;
import com.example.thomb.scanner.Helpers.HelperMethods;
import com.example.thomb.scanner.Listeners.ScanButtonListener;
import com.example.thomb.scanner.Listeners.SkuEnterKeyListener;
import com.example.thomb.scanner.Scanner;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.example.thomb.scanner.R;

import java.util.regex.Pattern;

public class AddStockScanBinOrProductActivity extends AppCompatActivity {

    private Button scanBinButton;
    private Button scanProductButton;
    private EditText SkuNumberEditText;
    private Intent binVerifiedIntent;
    private Intent productFoundIntent;

    private View skuText;
    private SkuEnterKeyListener skuListener;
    private ScanButtonListener scanButtonListener;

    private GlobalAccessor ga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_scan_bin);
        ga = new GlobalAccessor(this.getApplicationContext(), AddStockScanBinOrProductActivity.this);

        skuText = findViewById(R.id.enter_SKU_txt);
        Intent skuEnterIntent = new Intent(this.getApplicationContext(), AddStockProductIdentifiedActivity.class);
        skuListener = new SkuEnterKeyListener(this.getApplicationContext(), this, skuEnterIntent);
        skuText.setOnKeyListener(skuListener);

        scanBinButton = findViewById(R.id.scanBinButton);
        scanButtonListener = new ScanButtonListener(this.getApplicationContext(), this);
        scanBinButton.setOnClickListener(scanButtonListener);

        View scanOrSku = findViewById(R.id.outerScanOrsku);
        scanProductButton = scanOrSku.findViewById(R.id.scan_barcode);
        scanProductButton.setOnClickListener(scanButtonListener);

        SkuNumberEditText = findViewById(R.id.enter_SKU_txt);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            String scanContents = result.getContents();
            if(result.getContents() == null)
            {
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_LONG).show();
            }
            else
            {
                HelperMethods helper = new HelperMethods();
                if(helper.isGuid(scanContents))
                {
                    ga.setBinIdInStockObj(scanContents);
                    binVerifiedIntent = new Intent(AddStockScanBinOrProductActivity.this, AddStockBinVerifiedActivity.class);
                    startActivity(binVerifiedIntent);
                }
                else
                {
                    ga.setBarcodeInStockObj(scanContents);
                    productFoundIntent = new Intent(AddStockScanBinOrProductActivity.this, AddStockProductIdentifiedActivity.class);
                    startActivity(productFoundIntent);
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
