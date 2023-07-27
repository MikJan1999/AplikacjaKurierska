package com.example.aplikacjakurierska.ActivityClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;


import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
import com.example.aplikacjakurierska.ActivityCustomer.AdvertisementAdapter;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.GeneralAdvertisementApi;
import com.example.aplikacjakurierska.retrofit.model.GeneralAdvertisement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainNewActivity extends AppCompatActivity {

Button buttonViewProduct;
private List<GeneralAdvertisement> generalAdvertisements;
Animation scaleUp,scaleDown;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_new);
            viewListProduct();
buttonViewProduct = findViewById(R.id.buttonViewProduct);
scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_button);
scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down_button);

buttonViewProduct.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            buttonViewProduct.startAnimation(scaleUp);
        }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
            buttonViewProduct.startAnimation(scaleDown);
        }return false;
    }
});}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.new_menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.locationmenu:
                Intent locationmenu = new Intent(this,OrderAdressActivity.class);
            this.startActivity(locationmenu);
            return true;
            case R.id.addproductmenu:
                Intent add = new Intent(this,ChooseProductActivity.class);
                this.startActivity(add);
                return true;
            case R.id.shoppingcartmenu:
                Intent shoppingcartmenu = new Intent(this,OrderAcitivty.class);
                this.startActivity(shoppingcartmenu);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void getGeneralAdvertisement(){
        RecyclerView generalAdvertisement = (RecyclerView) findViewById(R.id.recycle_advert);

        }



    private void viewListProduct() {
        RecyclerView recyclerAdvert = findViewById(R.id.recycle_advert);


        RetrofitServ retrofitServ = new RetrofitServ();
        GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerAdvert.setLayoutManager(linearLayoutManager);
//        recyclertest.setLayoutManager(new LinearLayoutManager(this));


        generalAdvertisementApi.getAll().enqueue(new Callback<List<GeneralAdvertisement>>() {
            @Override
            public void onResponse(Call<List<GeneralAdvertisement>> call, Response<List<GeneralAdvertisement>> response) {
                if(response.isSuccessful()){
                    List<GeneralAdvertisement> advertResponse = response.body();
                    if(advertResponse !=null){
                        generalAdvertisements = advertResponse;
                        if(!generalAdvertisements.isEmpty()){
                            AdvertisementAdapter advertisementAdapter = new AdvertisementAdapter(generalAdvertisements);
                            recyclerAdvert.setAdapter(advertisementAdapter);
                        }else {
                            System.out.println("Pusta lista");
                        }
                    }else {
                        System.out.println("odpowiddz Api jest pusta");
                    }
                }else {
                    System.out.println("błąd odpowiedzi");
                }
                Toast.makeText(MainNewActivity.this, "pomylsnie zapisano", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<GeneralAdvertisement>> call, Throwable t) {
                Toast.makeText(MainNewActivity.this, "Nie zapisano jest bład", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Dupa");
            }
        }) ;
    }


}