package com.example.thomb.scanner.AddingActivities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.Listeners.ScanButtonListener;
import com.example.thomb.scanner.Listeners.SkuEnterKeyListener;
import com.example.thomb.scanner.Scanner;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.example.thomb.scanner.R;

import java.util.regex.Pattern;

public class AddStockScanBinOrProductActivity extends AppCompatActivity {

    Button scanBinButton;
    Button scanProductButton;
    private EditText SkuNumberEditText;
    private String SkuNumber;
    private Intent binVerifiedIntent;

    private View skuText;
    private SkuEnterKeyListener skuListener;
    private Button scanButton;
    private ScanButtonListener scanButtonListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_scan_bin);

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
                if(isGuid(scanContents))
                {
                    Toast.makeText(this, scanContents + " is a bin", Toast.LENGTH_LONG).show();
                    setBinIdInStockObj(scanContents);
                    binVerifiedIntent = new Intent(AddStockScanBinOrProductActivity.this, AddStockBinVerifiedActivity.class);
                    startActivity(binVerifiedIntent);
                    //show bin confirmed and sku/scan
                }
                else
                {
                    Toast.makeText(this, scanContents + " is a product", Toast.LENGTH_LONG).show();
                    setBarcodeInStockObj(scanContents);
                    //show product verified and scan bin
                }

            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean isGuid(String scanContents)
    {
        String guidRegex = "[0-9a-f]{8}[-][0-9a-f]{4}[-][0-9a-f]{4}[-][0-9a-f]{4}[-][0-9a-f]{12}";
        return Pattern.matches(guidRegex, scanContents);
    }

    private void setSKUinStockObj(String SKU)
    {
        StockQuantityUpdate stockUpdateObj = new StockQuantityUpdate();
        stockUpdateObj.setSKU(SKU);
        ((Scanner) this.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }

    private void setBarcodeInStockObj(String barcode)
    {
        StockQuantityUpdate stockUpdateObj = new StockQuantityUpdate();
        stockUpdateObj.setBarcode(barcode);
        ((Scanner) this.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }

    private void setBinIdInStockObj(String binId)
    {
        StockQuantityUpdate stockUpdateObj = new StockQuantityUpdate();
        stockUpdateObj.setBinId(binId);
        ((Scanner) this.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }
}
