package com.example.thomb.scanner.Helpers;

import android.app.Activity;
import android.content.Context;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.Scanner;

/**
 * Created by spam_ on 26/07/2018.
 */

public class GlobalAccessor {

    private Context context;
    private Activity callerActivity;

    public GlobalAccessor(Context context, Activity activity)
    {
        this.context = context;
        this.callerActivity = activity;
    }

    public void setSKUinStockObj(String SKU)
    {

        StockQuantityUpdate stockUpdateObj = ((Scanner) context.getApplicationContext()).getStockQtyUpdate();
        stockUpdateObj.setSKU(SKU);
        ((Scanner) callerActivity.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }

    public void setBarcodeInStockObj(String barcode)
    {
        StockQuantityUpdate stockUpdateObj = ((Scanner) context.getApplicationContext()).getStockQtyUpdate();
        stockUpdateObj.setBarcode(barcode);
        ((Scanner) callerActivity.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }

    public void getBinNameFromStockObj()
    {

    }
}
