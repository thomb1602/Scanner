package com.example.thomb.scanner.Listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.thomb.scanner.AddingActivities.AddStockHowManyActivity;
import com.example.thomb.scanner.Helpers.GlobalAccessor;
import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Created by spam_ on 28/07/2018.
 */

public class ScanButtonListener implements View.OnClickListener
{
    private Context context;
    private Activity callerActivity;

    public ScanButtonListener(Context context, Activity activity)
    {
        this.context = context;
        this.callerActivity = activity;
    }

    @Override
    public void onClick(View view)
    {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this.callerActivity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scan");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();
    }

}