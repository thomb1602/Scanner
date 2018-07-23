package com.example.thomb.scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;

public class PutawayBinVerifiedActivity extends AppCompatActivity {

    private String binName;
    private EditText howManyInput;
    private int howMany;
    private Intent putawayCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_putaway_bin_verified);

        this.binName = getIntent().getStringExtra("binName");

        TextView binNameLabel = findViewById(R.id.bin_name_label);
        binNameLabel.setText(this.binName);

        howManyInput = findViewById(R.id.how_many_input);
        howManyInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if(keyCode == KeyEvent.KEYCODE_ENTER)
                    {
                        howMany = Integer.valueOf(howManyInput.getText().toString());
                        putawayCompleted = new Intent(PutawayBinVerifiedActivity.this, PutawayCompletedActivity.class);
                        setQuantityInStockObj(howMany);
                        startActivity(putawayCompleted);
                    }
                }
                return false;
            }
        });

    }

    private void setQuantityInStockObj(int quantity)
    {
        StockQuantityUpdate stockUpdateObj = ((Scanner) this.getApplication()).getStockQtyUpdate();
        stockUpdateObj.setQuantity(quantity);
        ((Scanner) this.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }
}
