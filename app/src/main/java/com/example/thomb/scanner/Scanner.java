package com.example.thomb.scanner;

import android.app.Application;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;

/**
 * Created by spam_ on 26/06/2018.
 */

public class Scanner extends Application {

    private StockQuantityUpdate stockQtyUpdate;

    public Scanner()
    {
        setStockQtyUpdate(new StockQuantityUpdate());
    }

    public StockQuantityUpdate getStockQtyUpdate() {
        return stockQtyUpdate;
    }

    public void setStockQtyUpdate(StockQuantityUpdate stockQtyUpdate) {
        this.stockQtyUpdate = stockQtyUpdate;
    }
}
