package com.example.aplikacjakurierska.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.aplikacjakurierska.R;

public class OrderAdressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_order);
        Button backtoMainButton = (Button) findViewById(R.id.backToMain);
        backtoMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderAdressActivity.this,MainNewActivity.class));
            }
        });
    }




}
