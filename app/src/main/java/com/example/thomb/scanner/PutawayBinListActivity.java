package com.example.thomb.scanner;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.thomb.scanner.DataModels.StockQuantityUpdate;
import com.example.thomb.scanner.DatabaseThreads.ProductBinsByBarcodeThread;
import com.example.thomb.scanner.DataModels.BinProductList;

import java.util.ArrayList;
import java.util.List;


public class PutawayBinListActivity extends AppCompatActivity {

    private String SKU;
    private String barcode;
    public List<BinProductList> BinsAndProductQuantities = new ArrayList<>();
    private Intent verifyBinByScan;

    @SuppressLint("HandlerLeak")
    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == 1)
            {
                Bundle b;
                b = msg.getData();
                BinProductList binProductObj = new BinProductList();
                binProductObj.setBinName(String.valueOf(b.getString("binName")));
                binProductObj.setProductBinListId(b.getInt("productBinListId"));
                binProductObj.setProductName(String.valueOf(b.getString("productName")));
                binProductObj.setProductQuantity(b.getInt("productQuantities"));
                binProductObj.setBinId(String.valueOf(b.getString("binId")));
                BinsAndProductQuantities.add(binProductObj);
            }
            else if(msg.what == 2) //sent after all actual messages have been sent
            {
                drawBinsAndQuantities();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.SKU = getIntent().getStringExtra("SKU");
        this.barcode = getIntent().getStringExtra("barcode");
        ProductBinsByBarcodeThread networkThread = new ProductBinsByBarcodeThread(mHandler, this.barcode, this.SKU);
        networkThread.start();

    }

    protected void drawBinsAndQuantities()
    {
        setContentView(R.layout.activity_putaway_bin_list);
        //table layout
        TableLayout tableLayout = findViewById(R.id.BinListCanvas);

        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

        //product name row
        TableRow productNameRow = new TableRow(this);
        productNameRow.setLayoutParams(rowParams);
        productNameRow.setGravity(Gravity.CENTER_HORIZONTAL);

        //create product name label, add to row, add row to table
        TextView productNameLabel = new TextView(this);
        String productName = this.BinsAndProductQuantities.get(0).getProductName();
        String productNameLabelString = getString(R.string.putting_away) + productName;
        productNameLabel.setText(productNameLabelString);
        productNameRow.addView(productNameLabel);
        tableLayout.addView(productNameRow);

        Space spacer = new Space(this);
        spacer.setLayoutParams(new TableRow.LayoutParams(100,50));
        tableLayout.addView(spacer);

        //screen purpose description row
        TableRow screenPurposeRow = new TableRow(this);
        screenPurposeRow.setLayoutParams(rowParams);
        screenPurposeRow.setGravity(Gravity.CENTER_HORIZONTAL);
        //create label, label to row, row to table
        TextView screenPurposeDescriptionLabel = new TextView(this);
        screenPurposeDescriptionLabel.setText(R.string.which_bins);
        screenPurposeRow.addView(screenPurposeDescriptionLabel);
        tableLayout.addView(screenPurposeRow);

        Space spacer2 = new Space(this);
        spacer2.setLayoutParams(new TableRow.LayoutParams(100,100));
        tableLayout.addView(spacer2);

        //add the bins and their quantities
        for(int i = 0; i < this.BinsAndProductQuantities.size(); i++)
        {
            TableRow binAndQuantityRow = new TableRow(this);
            binAndQuantityRow.setLayoutParams(rowParams);
            binAndQuantityRow.setGravity(Gravity.CENTER_HORIZONTAL);

            Button binNameButton = new Button(this);
            String binId = BinsAndProductQuantities.get(i).getBinId();
            String binName = BinsAndProductQuantities.get(i).getBinName();
            binNameButton.setText(binName);
            setOnClick(binNameButton, binId, binName);
            binAndQuantityRow.addView(binNameButton);


            TextView quantityInBin = new TextView(this);
            quantityInBin.setText(String.valueOf(BinsAndProductQuantities.get(i).getProductQuantity()));
            quantityInBin.setLayoutParams(new TableRow.LayoutParams(200, 100));
            quantityInBin.setGravity(Gravity.CENTER_HORIZONTAL);
            binAndQuantityRow.addView(quantityInBin);
            tableLayout.addView(binAndQuantityRow);

        }

    }

    public void setOnClick(final Button btn, final String binId, final String binName){
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                verifyBinByScan = new Intent(PutawayBinListActivity.this, VerifyBinByScanActivity.class);
                verifyBinByScan.putExtra("binId", binId);
                verifyBinByScan.putExtra("binName", binName);
                setBinIdInStockObj(binId);
                startActivity(verifyBinByScan);
            }
        });
    }

    private void setBinIdInStockObj(String binId)
    {
        StockQuantityUpdate stockUpdateObj = ((Scanner) this.getApplication()).getStockQtyUpdate();
        stockUpdateObj.setBinId(binId);
        ((Scanner) this.getApplication()).setStockQtyUpdate(stockUpdateObj);
    }
}
