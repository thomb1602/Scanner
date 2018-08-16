package com.example.thomb.scanner.Listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.thomb.scanner.Helpers.GlobalAccessor;

/**
 * Created by spam_ on 16/08/2018.
 */

public class ConfirmQuantityEnterKeyListener implements View.OnKeyListener {

    private Context context;
    private Activity callerActivity;
    private Intent intent;
    private int quantity;
    private GlobalAccessor ga;

    public ConfirmQuantityEnterKeyListener(Context context, Activity activity, Intent intent)
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
                EditText quantityText = (EditText) view;
                quantity = Integer.valueOf(quantityText.getText().toString());
                ga.setQuantityInStockObj(quantity);
                context.startActivity(this.intent);
            }
        }
        return false;
    }
}
