package com.example.thomb.scanner.DatabaseThreads;

import android.os.Message;

import android.os.Handler;
import android.util.JsonReader;

import com.example.thomb.scanner.DbTransactions;
import com.example.thomb.scanner.JSONFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by spam_ on 01/06/2018.
 */

public class ProductBinsByBarcodeThread extends Thread {

    private DbTransactions dbHelper = new DbTransactions();
    private JSONFormatter formatter = new JSONFormatter();
    private Handler hd;
    private String barcode;
    private String SKU;

    public ProductBinsByBarcodeThread(Handler msgHandler, String barcode, String SKU) {
        this.hd = msgHandler;
        this.barcode = barcode;
        this.SKU = SKU;
    }

    public void run() {

        List<Message> results = new ArrayList<Message>();
        try
        {
            String URLAndParam = "";
            if(this.SKU == null && this.barcode != null)
            {
                URLAndParam = "http://dyaswms.azurewebsites.net/api/api/GetBinListByProductBarcode?barcode='" + this.barcode + "'";
            }
            else
            {
                URLAndParam = "http://dyaswms.azurewebsites.net/api/api/GetBinListByProductSKU?barcode='" + this.SKU + "'";
            }
            results = FindProductBinsByBarcode(URLAndParam, this.hd);
        }
        catch(IOException e)
        {

        }

        for(Iterator<Message> i = results.iterator(); i.hasNext();) {
            Message msg = i.next();
            msg.what = 1;
            hd.sendMessage(msg);
        }
        hd.sendEmptyMessage(2);
    }

    public List<Message> FindProductBinsByBarcode(String URLAndParam, Handler hd) throws java.io.IOException
    {
        final String URLAndParameter = URLAndParam;
        List<Message> results = new ArrayList<Message>();
        try
        {
            HttpURLConnection conn = dbHelper.CreateGetConnection(URLAndParameter);
            int responseCode = conn.getResponseCode();
            if (responseCode == 200)
            {
                InputStream responseBody = conn.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                results = formatter.getBinProductQuantities(jsonReader, hd);
                conn.disconnect();
            }
            else if (responseCode == 500)
            {

            }
        }
        catch (IOException e)
        {

        }
        return results;
    }


}
