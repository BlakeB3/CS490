package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderSummary extends AppCompatActivity{

    //ArrayList<String> itemList;

    //ArrayAdapter<String> adapter;

    TextView itemText;

    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summary);

        Intent startingIntent = getIntent();
        String orderSummary = startingIntent.getStringExtra("orderSummary1");

        itemText = (TextView) findViewById(R.id.summaryText);
        itemText.setText(orderSummary);

        Button goToOrder = (Button) findViewById(R.id.goToOrder);
        goToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }

}
