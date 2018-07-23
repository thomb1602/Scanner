package com.example.thomb.scanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class VerifyBinByScanActivity extends AppCompatActivity {

    Button scanButton;
    Intent binVerified;
    String binId;
    String binName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binId = getIntent().getStringExtra("binId");
        this.binName = getIntent().getStringExtra("binName");
        setContentView(R.layout.activity_verify_bin_by_scan);
        scanButton = findViewById(R.id.scan_bin_button);
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
            String binId = result.getContents();
            if(binId == null)
            {
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_LONG).show();
            }
            else
            {
                if(this.binId.equals(binId))
                {
                    binVerified = new Intent(VerifyBinByScanActivity.this, PutawayBinVerifiedActivity.class);
                    binVerified.putExtra("binName", this.binName);
                    startActivity(binVerified);
                }
                else
                {
                    Toast.makeText(this, "Bin could not be verified. Please try again", Toast.LENGTH_LONG).show();
                }

            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
