package com.example.thomb.scanner.LayoutControllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.PutawayActivity;
import com.example.thomb.scanner.PutawayBinListActivity;
import com.example.thomb.scanner.R;
import com.example.thomb.scanner.Scanner;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by spam_ on 26/07/2018.
 */

public class ScanOrSkuController extends AppCompatActivity {

    private View skuOrScan;
    private Button scanButton;
    private TextView skuLabel;
    private EditText skuText;

    private String skuNumber;
    private String productBarcode;

    //will this work?
    public void setListeners()
    {
        setContentView(R.layout.activity_add_stock_bin_verified);

        skuOrScan = findViewById(R.id.scan_or_sku);
        scanButton = findViewById(R.id.scan_barcode);
        skuText = findViewById(R.id.enter_SKU_txt);

        skuText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if(keyCode == KeyEvent.KEYCODE_ENTER)
                    {
                        skuNumber = skuText.getText().toString();
                        //TODO: do this in it's own class somewhere
                        //setSKUinStockObj(skuNumber);
                    }
                }
                return false;
            }
        });

        scanButton = findViewById(R.id.scan_barcode);
        final Activity activity = this;
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            String barcode = result.getContents();
            if(result.getContents() == null)
            {
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_LONG).show();
            }
            else
            {
                productBarcode = barcode;
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}

