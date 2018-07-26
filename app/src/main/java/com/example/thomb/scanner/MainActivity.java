package com.example.thomb.scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thomb.scanner.AddingActivities.AddStockScanBinOrProductActivity;

public class MainActivity extends AppCompatActivity {

    Button putawayButton;
    Intent putawayIntent;
    Button findButton;
    Intent findIntent;
    Button addButton;
    Intent addIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        putawayButton = findViewById(R.id.putawayButton);
        putawayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putawayIntent = new Intent(MainActivity.this, PutawayActivity.class);
                startActivity(putawayIntent);
            }
        });

        findButton = findViewById(R.id.findButton);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIntent = new Intent(MainActivity.this, AddStockScanBinOrProductActivity.class);
                startActivity(addIntent);
            }
        });

    }
}
