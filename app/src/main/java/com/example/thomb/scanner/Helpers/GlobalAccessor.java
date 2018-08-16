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
    private StockQuantityUpdate stockUpdateObj;

    public GlobalAccessor(Context context, Activity activity)
    {
        this.context = context;
        this.callerActivity = activity;
        this.stockUpdateObj = ((Scanner) context.getApplicationContext()).getStockQtyUpdate();
    }

    public void setBinIdInStockObj(String binId)
    {
        stockUpdateObj.setBinId(binId);
        ((Scanner) callerActivity.getApplication()).setStockQtyUpdate(stockUpdateObj);

    }

    public void setSKUinStockObj(String SKU)
    {
        stockUpdateObj.setSKU(SKU);
        ((Scanner) callerActivity.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }

    public void setBarcodeInStockObj(String barcode)
    {
        stockUpdateObj.setBarcode(barcode);
        ((Scanner) callerActivity.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }

    public void getBinNameFromStockObj()
    {

    }

    public void setQuantityInStockObj(int quantity)
    {
        stockUpdateObj.setQuantity(quantity);
        ((Scanner) callerActivity.getApplication()).setStockQtyUpdate(stockUpdateObj);

    }
}
