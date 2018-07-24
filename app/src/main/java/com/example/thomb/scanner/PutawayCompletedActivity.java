package com.example.thomb.scanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.DatabaseThreads.PutawayUpdateDbThread;

public class PutawayCompletedActivity extends AppCompatActivity {

    private Button nextButton;
    private Button finishedButton;

    @SuppressLint("HandlerLeak")
    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg)
        {
            //send just one message from thread to confirm whether data in db has been updates successfully
            if(msg.what == 1)
            {
                setLayout();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StockQuantityUpdate stockUpdateObj = ((Scanner) this.getApplication()).getStockQtyUpdate();

        PutawayUpdateDbThread thread = new PutawayUpdateDbThread(mHandler, stockUpdateObj);
        thread.start();
    }

    private void setLayout()
    {
        setContentView(R.layout.activity_putaway_completed);
        nextButton = findViewById(R.id.next_button);
        finishedButton = findViewById(R.id.finished_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(PutawayCompletedActivity.this, PutawayActivity.class);
                startActivity(nextIntent);
            }
        });
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent finishedIntent = new Intent(PutawayCompletedActivity.this, MainActivity.class);
                startActivity(finishedIntent);
            }
        });
    }
}
