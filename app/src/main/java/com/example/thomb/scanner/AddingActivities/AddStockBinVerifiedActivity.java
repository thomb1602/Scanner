package com.example.thomb.scanner.AddingActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomb.scanner.Helpers.GlobalAccessor;
import com.example.thomb.scanner.Helpers.HelperMethods;
import com.example.thomb.scanner.Listeners.ScanButtonListener;
import com.example.thomb.scanner.Listeners.SkuEnterKeyListener;
import com.example.thomb.scanner.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddStockBinVerifiedActivity extends AppCompatActivity {

    private View scanOrSku;
    private SkuEnterKeyListener skuListener;
    private Button scanButton;
    private ScanButtonListener scanButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_bin_verified);

        scanOrSku = findViewById(R.id.enter_SKU_txt);
        Intent skuEnterIntent = new Intent(this.getApplicationContext(), AddStockHowManyActivity.class);
        skuListener = new SkuEnterKeyListener(this.getApplicationContext(), this, skuEnterIntent);
        scanOrSku.setOnKeyListener(skuListener);

        scanButton = findViewById(R.id.scan_barcode);
        scanButtonListener = new ScanButtonListener(this.getApplicationContext(), this);
        scanButton.setOnClickListener(scanButtonListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            HelperMethods helper = new HelperMethods();
            String barcode = result.getContents();
            if(result.getContents() == null)
            {
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_LONG).show();
            }
            else
            {
                if(helper.isGuid(barcode))
                {
                    GlobalAccessor ga = new GlobalAccessor(this.getApplicationContext(), this);
                    ga.setBarcodeInStockObj(barcode);
                    startActivity(new Intent(AddStockBinVerifiedActivity.this, AddStockHowManyActivity.class));
                }
                else
                {
                    Toast.makeText(AddStockBinVerifiedActivity.this, "Bin ID not recognised", Toast.LENGTH_LONG).show();
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
