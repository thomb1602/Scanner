package com.example.thomb.scanner;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DbTransactions
{

    public HttpURLConnection CreateGetConnection(String URLAndParam)
    {
        HttpURLConnection conn = null;
        try
        {
            URL url = new URL(URLAndParam);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "DyasWMSApp");
            conn.setRequestMethod("GET");

        }
        catch (IOException e)
        {

        }
        return conn;
    }

    public HttpURLConnection CreatePostConnection(String Url) throws java.net.MalformedURLException
    {
        HttpURLConnection conn = null;
        URL url = new URL(Url);
        try
        {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //conn.setRequestMethod();
            conn.setDoInput(true);
            conn.setDoOutput(true);
        }
        catch(IOException e)
        {

        }
        return conn;
    }
}
