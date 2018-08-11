package com.example.thomb.scanner.Listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.thomb.scanner.AddingActivities.AddStockHowManyActivity;
import com.example.thomb.scanner.Helpers.GlobalAccessor;

/**
 * Created by spam_ on 28/07/2018.
 */

public class SkuEnterKeyListener implements View.OnKeyListener
{
    private Context context;
    private Activity callerActivity;
    private Intent intent;
    private String SkuNumber;
    private GlobalAccessor ga;

    public SkuEnterKeyListener(Context context, Activity activity, Intent intent)
    {
        this.context = context;
        this.callerActivity = activity;
        this.intent = intent;
        ga = new GlobalAccessor(this.context, this.callerActivity);
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent)
    {
        if(keyEvent.getAction() == KeyEvent.ACTION_DOWN)
        {
            if(keyCode == KeyEvent.KEYCODE_ENTER)
            {
                EditText skuText = (EditText) view;
                SkuNumber = skuText.getText().toString();
                ga.setSKUinStockObj(SkuNumber);
                context.startActivity(this.intent);
            }
        }
        return false;
    }

}