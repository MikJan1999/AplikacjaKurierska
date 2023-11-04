package com.example.aplikacjakurierska.ActivityClient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.PositionCustomerOrderApi;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import kotlin.text.Regex;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdressActivity extends AppCompatActivity {
    private float priceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_order);
            addOrderAddressClient();
            summPriceAll();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderAdressActivity.this, OrderAcitivty.class);
                startActivity(intent);
            }
        });
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

    }

    public void summPriceAll(){
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        Long userId = sp.getLong("id",0);

        RetrofitServ retrofitServ = new RetrofitServ();
        PositionCustomerOrderApi positionCustomerOrderApi = retrofitServ.getRetrofit().create(PositionCustomerOrderApi.class);
        positionCustomerOrderApi.getAllPositionCustomerOrdersWithProductNamesByUserId("Bearer "+token1,userId ).enqueue(new Callback<List<PositionCustomerOrderWithProductNameDTO>>() {
            @Override
            public void onResponse(Call<List<PositionCustomerOrderWithProductNameDTO>> call, Response<List<PositionCustomerOrderWithProductNameDTO>> response) {
                List<PositionCustomerOrderWithProductNameDTO> pozycje = response.body();

               priceOrder = pozycje.stream()
                        .map(PositionCustomerOrderWithProductNameDTO::getPriceAll)
                        .reduce(0f, Float::sum);

//                System.out.println("Suma cen wszystkich pozycji: " + priceOrder);
            }
            @Override
            public void onFailure(Call<List<PositionCustomerOrderWithProductNameDTO>> call, Throwable t) {

            }});}







    private void addOrderAddressClient() {
        SharedPreferences sp = getSharedPreferences("main", 0);
        String token1 = sp.getString("token", null);


        Button saveButton = findViewById(R.id.saveAddress);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText nameLastName = findViewById(R.id.name_lastName);
                TextInputEditText street = findViewById(R.id.streetAddress);
                TextInputEditText numberHouse = findViewById(R.id.numberhouseAddress);
                TextInputEditText village = findViewById(R.id.villageAddress);
                TextInputEditText phoneNumber = findViewById(R.id.numberPhoneAddress);
                
                Editable nameLastNameText = nameLastName.getText();
                System.out.println("'gettTEXT = "+ nameLastNameText);
                Editable streetText = street.getText();
                Editable houseText = numberHouse.getText();
                Editable villageText = village.getText();
                String s = phoneNumber.getText().toString();
                if (!s.matches("\\d+")){
                    Toast.makeText(OrderAdressActivity.this, "Nieprawidłowy numer telefonu", Toast.LENGTH_SHORT).show();
                }else{Editable phoneNumberText =  phoneNumber.getText();

                    Intent addressData = new Intent(OrderAdressActivity.this, SummaryOrderActivity.class);
                    addressData.putExtra("nameLastName",nameLastNameText.toString());
                    addressData.putExtra("street",streetText.toString());
                    addressData.putExtra("numberHouse",houseText.toString());
                    addressData.putExtra("village",villageText.toString());
                    addressData.putExtra("nummberPhone",phoneNumberText.toString());
                    addressData.putExtra("priceOrder",priceOrder);
                    System.out.println(priceOrder);
                    startActivity(addressData);
                }




            }
        });


    }}
