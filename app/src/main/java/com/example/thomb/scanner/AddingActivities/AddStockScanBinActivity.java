package com.example.thomb.scanner.AddingActivities;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.example.thomb.scanner.R;

import java.util.regex.Pattern;

public class AddStockScanBinActivity extends AppCompatActivity {

    Button scanBinButton;
    Button scanProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_scan_bin);
        scanBinButton = findViewById(R.id.scanBinButton);
        scanProductButton = findViewById(R.id.scanProductButton);

        final Activity scanActivity = this;

        scanBinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(scanActivity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
            }
        });

        scanProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(scanActivity);
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
                }
                else
                {
                    Toast.makeText(this, scanContents + " is a product", Toast.LENGTH_LONG).show();
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
}
