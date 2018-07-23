package com.example.thomb.scanner;


import android.app.Activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class PutawayActivity extends AppCompatActivity {

    private Button scanButton;
    private Intent binListIntent;
    private EditText SkuNumberEditText;
    private String SkuNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putaway);

        SkuNumberEditText = findViewById(R.id.enter_SKU_txt);
        SkuNumberEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if(keyCode == KeyEvent.KEYCODE_ENTER)
                    {
                        SkuNumber = SkuNumberEditText.getText().toString();
                        setSKUinStockObj(SkuNumber);
                        binListIntent = new Intent(PutawayActivity.this, PutawayBinListActivity.class);
                        binListIntent.putExtra("SKU", SkuNumber);
                        startActivity(binListIntent);
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
                setBarcodeInStockObj(barcode);
                binListIntent = new Intent(PutawayActivity.this, PutawayBinListActivity.class);
                binListIntent.putExtra("barcode", barcode);
                startActivity(binListIntent);
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
}
