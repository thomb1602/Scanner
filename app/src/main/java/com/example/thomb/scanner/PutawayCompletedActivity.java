package com.example.thomb.scanner;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.thomb.scanner.DataModels.BinProductList;
import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.DatabaseThreads.PutawayUpdateDbThread;

public class PutawayCompletedActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg)
        {
            //send just one message from thread to confirm whether data in db has been updates successfully
            if(msg.what == 1)
            {
                Bundle b;
                b = msg.getData();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StockQuantityUpdate stockUpdateObj = ((Scanner) this.getApplication()).getStockQtyUpdate();

        PutawayUpdateDbThread thread = new PutawayUpdateDbThread(mHandler, stockUpdateObj);
        thread.start();
        setContentView(R.layout.activity_putaway_completed);
    }
}
