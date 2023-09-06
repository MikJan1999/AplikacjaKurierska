package com.example.aplikacjakurierska.ActivityClient;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.iapi.ProductApi;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.model.Product;


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
//        loadDataProduct();
        backtoMainButton1 = (Button) findViewById(R.id.buttonBackToProduct);
        backtoMainButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderAcitivty.this,MainNewActivity.class));
            }});}

//    private void loadDataProduct() {
//        RetrofitServ retrofitServ = new RetrofitServ();
//        ProductApi itemApi = retrofitServ.getRetrofit().create(ProductApi.class);
//        itemApi.getAll().enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//            populateListView(response.body());
//                Toast.makeText(OrderAcitivty.this, "Succesfull", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Toast.makeText(OrderAcitivty.this, "Failed to load product", Toast.LENGTH_SHORT).show();
//                Logger.getLogger(OrderAcitivty.class.getName()).log(Level.SEVERE,"error occured",t);
//            }
//        });
//
//    }
    private void populateListView(List<Product> productsList) {

    }



}