package com.example.aplikacjakurierska.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;



import com.example.aplikacjakurierska.R;

public class ChooseProductActivity extends AppCompatActivity {

NumberPicker nameProductPicker;
TextView product;
String [] products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);
addToPicker();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.locationmenu:
                Intent i = new Intent(this,OrderAdressActivity.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addToPicker(){
        product = findViewById(R.id.product);
        nameProductPicker = findViewById(R.id.nameProductPicker);

        nameProductPicker.setMinValue(0);
        nameProductPicker.setMaxValue(12);
        nameProductPicker.setDisplayedValues(products);
        product.setText(String.format("Produkt: %s",products[nameProductPicker.getValue()] ));
nameProductPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        product.setText(String.format("Produkt: &s",products[i1]));
    }
});

        }
 }