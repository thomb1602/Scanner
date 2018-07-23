package com.example.thomb.scanner.DataModels;

/**
 * Created by spam_ on 03/06/2018.
 */

public class BinProductList {

    private String binId;
    private int productBinListId;
    private String binName;
    private String productName;
    private int productQuantity;

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public String getBinId() {
        return binId;
    }

    public void setProductBinListId(int productBinListId) {
        this.productBinListId = productBinListId;
    }

    public void setBinName(String binName) {
        this.binName = binName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductBinListId() {
        return productBinListId;
    }

    public String getBinName() {
        return binName;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }
}

