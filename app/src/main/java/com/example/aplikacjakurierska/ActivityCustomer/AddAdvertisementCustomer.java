package com.example.aplikacjakurierska.ActivityCustomer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityClient.MainNewActivity;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.GeneralAdvertisementApi;
import com.example.aplikacjakurierska.retrofit.model.GeneralAdvertisement;
import com.example.aplikacjakurierska.retrofit.model.Product;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAdvertisementCustomer extends AppCompatActivity {
    private List<GeneralAdvertisement> generalAdvertisements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_advertisement_customer);

addProductCustomers();
viewListProduct();
    }

    private void addProductCustomers() {
        TextInputEditText advert = findViewById(R.id.advertisementaddtext);

        AppCompatButton buttonAddAdvert = findViewById(R.id.buttonAddAdvertisement);



        RetrofitServ retrofitServ = new RetrofitServ();
        GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
        buttonAddAdvert.setOnClickListener(view -> {
            String adverts = String.valueOf(advert.getText());

            GeneralAdvertisement generalAdvertisement = new GeneralAdvertisement();
            generalAdvertisement.setAdvertisement(adverts);

            generalAdvertisementApi.add(generalAdvertisement).enqueue(new Callback<GeneralAdvertisement>() {
                @Override
                public void onResponse(Call<GeneralAdvertisement> call, Response<GeneralAdvertisement> response) {
                    Toast.makeText(AddAdvertisementCustomer.this, "Pomyślnie zapisano produkt", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<GeneralAdvertisement> call, Throwable t) {
                    Toast.makeText(AddAdvertisementCustomer.this, "Nie zapisano produktu", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(AddingProductsCustomerActivity.class.getName() ).log(Level.SEVERE,"Error ");
                }
            });

        });


    }


    public void viewListProduct() {
        RecyclerView recyclerAdvert = findViewById(R.id.recycle_advert);


        RetrofitServ retrofitServ = new RetrofitServ();
        GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerAdvert.setLayoutManager(linearLayoutManager);



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
                Toast.makeText(AddAdvertisementCustomer.this, "pomylsnie zapisano", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<GeneralAdvertisement>> call, Throwable t) {
                Toast.makeText(AddAdvertisementCustomer.this, "Nie zapisano jest bład", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Niepowodzenie");
            }
        }) ;
    }


}
