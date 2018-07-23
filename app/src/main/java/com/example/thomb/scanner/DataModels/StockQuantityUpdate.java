package com.example.thomb.scanner.DataModels;

/**
 * Created by spam_ on 22/06/2018.
 */

public class StockQuantityUpdate {


    private String binId;
    private String SKU;
    private String barcode;
    private int quantity;

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        if(barcode == null)
        {
            return "";
        }
        else
        {
            return barcode;
        }

    }

    public String getBinId() {

        return binId;
    }

    public String getSKU() {
        if(SKU == null)
        {
            return "";
        }
        else
        {
            return SKU;

        }
    }

    public int getQuantity() {
        return quantity;
    }
}
