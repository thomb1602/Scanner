package com.example.thomb.scanner.AddingActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thomb.scanner.Helpers.GlobalAccessor;
import com.example.thomb.scanner.Listeners.ConfirmQuantityEnterKeyListener;
import com.example.thomb.scanner.R;

public class AddStockHowManyActivity extends AppCompatActivity {

    private GlobalAccessor ga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_how_many);

        View confirmQuantityGroup = findViewById(R.id.confirm_quantity);
        TextView confirmQuantityCaption = confirmQuantityGroup.findViewById(R.id.quantity_caption);
        confirmQuantityCaption.setText(R.string.how_many_of_product);

        Intent additionVerifiedIntent = new Intent(AddStockHowManyActivity.this, AddStockAdditionVerifiedActivity.class);
        ConfirmQuantityEnterKeyListener confirmQuantityListener = new ConfirmQuantityEnterKeyListener(this.getApplicationContext(), this, additionVerifiedIntent);
        EditText enterQuantityText = confirmQuantityGroup.findViewById(R.id.quantity_text);
        enterQuantityText.setOnKeyListener(confirmQuantityListener);

    }
}
