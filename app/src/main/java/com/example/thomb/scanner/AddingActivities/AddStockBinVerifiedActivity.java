package com.example.thomb.scanner.AddingActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomb.scanner.Helpers.GlobalAccessor;
import com.example.thomb.scanner.Listeners.ScanButtonListener;
import com.example.thomb.scanner.Listeners.SkuEnterKeyListener;
import com.example.thomb.scanner.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddStockBinVerifiedActivity extends AppCompatActivity {

    private View scanOrSku;
    private View binVerified;
    private SkuEnterKeyListener skuListener;
    private Button scanButton;
    private ScanButtonListener scanButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_bin_verified);

        //scan_or_sku
        scanOrSku = findViewById(R.id.enter_SKU_txt);
        Intent skuEnterIntent = new Intent(this.getApplicationContext(), AddStockHowManyActivity.class);
        skuListener = new SkuEnterKeyListener(this.getApplicationContext(), this, skuEnterIntent);
        scanOrSku.setOnKeyListener(skuListener);

        scanButton = findViewById(R.id.scan_barcode);
        scanButtonListener = new ScanButtonListener(this.getApplicationContext(), this);
        scanButton.setOnClickListener(scanButtonListener);

        //bin_verified
        binVerified = findViewById(R.id.bin_name_verified);
        TextView binVerifiedCaption = findViewById(R.id.bin_verified_caption);
        binVerifiedCaption.setText(R.string.how_many_of_product);

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
                Toast.makeText(this, barcode, Toast.LENGTH_LONG).show();
                GlobalAccessor ga = new GlobalAccessor(this.getApplicationContext(), this);
                ga.setBarcodeInStockObj(barcode);
                startActivity(new Intent(AddStockBinVerifiedActivity.this, AddStockHowManyActivity.class));
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void createCaption()
    {
        GlobalAccessor ga = new GlobalAccessor(this.getApplicationContext(), this);

    }
}
