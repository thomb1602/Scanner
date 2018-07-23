package com.example.thomb.scanner;

import android.os.Bundle;
import android.os.Message;
import android.util.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;

import org.json.JSONException;
import org.json.JSONObject;


public class JSONFormatter {

    public List<Message> getBinProductQuantities(JsonReader jReader, Handler hd) throws IOException
    {
        List<Message> messages = new ArrayList<>();
        jReader.beginArray();
        while (jReader.hasNext())
        {
            messages.add(readBinProductQuantities(jReader, hd));
        }
        return messages;
    }

    public Message readBinProductQuantities(JsonReader reader, Handler hd) throws IOException {
        reader.beginObject();
        Bundle b = new Bundle();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("BinName")) {
                b.putString("binName", reader.nextString());
            }
            else if (name.equals("BinId"))
            {
                b.putString("binId", reader.nextString());
            }
            else if (name.equals("ProductBinListId")) {
                b.putInt("productBinListId", reader.nextInt());
            } else if (name.equals("ProductName")) {
                b.putString("productName", reader.nextString());
            } else if (name.equals("ProductQuantities")) {
                b.putInt("productQuantities", reader.nextInt());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Message msg = hd.obtainMessage();
        msg.setData(b);
        return msg;
    }

    public String createForUpdatePutawayQuantity(StockQuantityUpdate stockUpdateObj) throws JSONException
    {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("binId", stockUpdateObj.getBinId());
        jsonObj.put("barcode", stockUpdateObj.getBarcode());
        jsonObj.put("SKU", stockUpdateObj.getSKU());
        jsonObj.put("quantity", stockUpdateObj.getQuantity());
        return jsonObj.toString();
    }
}
