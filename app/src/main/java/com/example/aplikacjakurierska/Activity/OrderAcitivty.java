package com.example.aplikacjakurierska.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.adapter.ProductAdapter;
import com.example.aplikacjakurierska.model.Product;
import com.example.aplikacjakurierska.model.ProductApi;
import com.example.aplikacjakurierska.model.RetrofitServ;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderAcitivty extends AppCompatActivity {

private RecyclerView recyclerView;
    Button backtoMainButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
recyclerView = findViewById(R.id.product_list_recycler);
    loadDataProduct();


        backtoMainButton1 = (Button) findViewById(R.id.buttonBackToProduct);
        backtoMainButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderAcitivty.this,MainNewActivity.class));
            }
        });
    }




    private void loadDataProduct() {
        RetrofitServ retrofitServ = new RetrofitServ();
        ProductApi productApi = retrofitServ.getRetrofit().create(ProductApi.class);
        productApi.allProduct()
        .enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            populateListView(response.body());
                Toast.makeText(OrderAcitivty.this, "Zajebiśćie", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(OrderAcitivty.this, "Failed to load product", Toast.LENGTH_SHORT).show();
                Logger.getLogger(OrderAcitivty.class.getName()).log(Level.SEVERE,"error oCuder",t);
            }
        });

    }
    private void populateListView(List<Product> productList) {
        ProductAdapter productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}