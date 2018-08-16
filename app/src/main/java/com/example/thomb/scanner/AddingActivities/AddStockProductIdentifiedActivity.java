package com.example.thomb.scanner.AddingActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.thomb.scanner.Helpers.GlobalAccessor;
import com.example.thomb.scanner.Listeners.ScanButtonListener;
import com.example.thomb.scanner.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddStockProductIdentifiedActivity extends AppCompatActivity {

    private Button scanButton;
    private ScanButtonListener scanButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_product_identified);

        scanButton = findViewById(R.id.scan_bin_button);
        scanButtonListener = new ScanButtonListener(this.getApplicationContext(), this);
        scanButton.setOnClickListener(scanButtonListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            String binQrCode = result.getContents();
            if(result.getContents() == null)
            {
                Toast.makeText(this, "You cancelled scanning", Toast.LENGTH_LONG).show();
            }
            else
            {
                GlobalAccessor ga = new GlobalAccessor(this.getApplicationContext(), this);
                ga.setBarcodeInStockObj(binQrCode);
                Intent howManyIntent = new Intent(AddStockProductIdentifiedActivity.this, AddStockHowManyActivity.class);
                startActivity(howManyIntent);
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
