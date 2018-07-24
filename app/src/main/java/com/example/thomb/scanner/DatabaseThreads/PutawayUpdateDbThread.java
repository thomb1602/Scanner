package com.example.thomb.scanner.DatabaseThreads;

import android.os.Message;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.DbTransactions;
import com.example.thomb.scanner.JSONFormatter;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import android.os.Handler;

/**
 * Created by spam_ on 15/06/2018.
 */

public class PutawayUpdateDbThread extends Thread {

    private DbTransactions dbHelper = new DbTransactions();
    private JSONFormatter formatter = new JSONFormatter();
    private StockQuantityUpdate stockUpdateObj;
    private Handler hd;

    public PutawayUpdateDbThread(Handler handler, StockQuantityUpdate stockUpdateObj)
    {
        this.hd = handler;
        this.stockUpdateObj = stockUpdateObj;
    }

    public void run()
    {
        boolean success = false;
        try
        {
            success = putUpdateStockQuantity();
        }
        catch(IOException e)
        {

        }
        Message msg = new Message();
        if(success)
        {
            msg.what = 1;
        }
        else
        {
            msg.what = 2;
        }
        hd.sendMessage(msg);
    }

    private boolean putUpdateStockQuantity() throws java.io.IOException
    {
        final String URL = "http://dyaswms.azurewebsites.net/api/api/PutawayStockUpdateQuantity";
        HttpURLConnection conn = dbHelper.CreatePostConnection(URL);
        OutputStream outStream = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream, "UTF-8"));
        String postDataString = null;
        try
        {
            postDataString = formatter.createForUpdatePutawayQuantity(this.stockUpdateObj);
        }
        catch(JSONException e)
        {

        }
        writer.write(postDataString);
        writer.flush();
        writer.close();
        outStream.close();
        int responseCode = conn.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
