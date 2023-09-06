package com.example.aplikacjakurierska.ActivityClient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.AddressApi;
import com.example.aplikacjakurierska.retrofit.model.Address;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_order);
        Button backtoMainButton = (Button) findViewById(R.id.backToMain);
addOrderAddressClient();
        backtoMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderAdressActivity.this,MainNewActivity.class));
            }
        });
    }
    private void addOrderAddressClient() {
        SharedPreferences sp = getSharedPreferences("main",0);
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
                RetrofitServ retrofitServ = new RetrofitServ();
                AddressApi addressApi = retrofitServ.getRetrofit().create(AddressApi.class);
                saveButton.setOnClickListener(views -> {
                    String nameLastNames = String.valueOf(nameLastName.getText());
                    String streets = String.valueOf(street.getText());
                    String numberHouses = String.valueOf(numberHouse.getText());
                    String villages = String.valueOf(village.getText());
                    String phoneNumbers = String.valueOf(phoneNumber.getText());

                    Address addressAdd = new Address();
                    addressAdd.setNameAndSurname(nameLastNames);
                    addressAdd.setStreet(streets);
                    addressAdd.setNumberOfHouse(Integer.parseInt(numberHouses));
                    addressAdd.setVillage(villages);
                    addressAdd.setNumberOfPhone(Integer.parseInt(phoneNumbers));

                    addressApi.add(token1,addressAdd).enqueue(new Callback<Address>() {
                        @Override
                        public void onResponse(Call<Address> call, Response<Address> response) {
                            Toast.makeText(OrderAdressActivity.this, "Pomyślnie zapisano adres", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onFailure(Call<Address> call, Throwable t) {
                            Toast.makeText(OrderAdressActivity.this, "Nie zapisano adresu", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE, "Error create new address ");
                        }
                    });
                });

            }
        });


    }



}
