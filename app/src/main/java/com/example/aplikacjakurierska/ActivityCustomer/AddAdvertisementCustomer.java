package com.example.aplikacjakurierska.ActivityCustomer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityClient.LoginActivity;
import com.example.aplikacjakurierska.ActivityClient.MainNewActivity;
import com.example.aplikacjakurierska.ActivityClient.OrderAcitivty;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.GeneralAdvertisementApi;
import com.example.aplikacjakurierska.retrofit.model.GeneralAdvertisement;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAdvertisementCustomer extends AppCompatActivity implements AdvertisementAdapter.OnStudyListener{
    private List<GeneralAdvertisement> generalAdvertisements;
    GeneralAdvertisement generalAdvertisement;
    AppCompatButton buttonAddAdvert;
    AdvertisementAdapter advertisementAdapter;
    private AdvertisementAdapter.OnStudyListener monStudylistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_advertisement_customer);
        advertisementAdapter = new AdvertisementAdapter(generalAdvertisements,monStudylistener,false);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAdvertisementCustomer.this, MainNewActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        monStudylistener = this;
        AppCompatButton buttonAddAdvert = findViewById(R.id.buttonEditAdvertisement);

        addAdvertCustomer();
viewListProduct();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.customer_menu, menu);
        return super.onCreateOptionsMenu(menu);    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.allordermenuCustomer) {
            Intent intent1 = new Intent(this, AllOrderActivity.class);
            this.startActivity(intent1);
            return true;
        }
        if (id == R.id.addProductMenuCustomer) {
            Intent intent2 = new Intent(this, AddingProductsCustomerActivity.class);
            this.startActivity(intent2);
            return true;
        }
        if (id == R.id.shoppingcartmenu) {
            Intent intent2 = new Intent(this, OrderAcitivty.class);
            this.startActivity(intent2);
            return true;
        }
        if (id == R.id.logoutMenuCustomer) {
            SharedPreferences sharedPreferences = getSharedPreferences("main", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent2 = new Intent(this, LoginActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }
    private void addAdvertCustomer() {
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        TextInputEditText advert = findViewById(R.id.advertisementaddtext);
        RetrofitServ retrofitServ = new RetrofitServ();
        GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
         buttonAddAdvert = findViewById(R.id.buttonEditAdvertisement);
        buttonAddAdvert.setOnClickListener(view -> {
            String adverts = String.valueOf(advert.getText());
            String toString = advert.getText().toString();
            if(toString.isEmpty()){
                Toast.makeText(AddAdvertisementCustomer.this, "Proszę wprowadzić tekst", Toast.LENGTH_SHORT).show();}
            else {
            GeneralAdvertisement generalAdvertisement = new GeneralAdvertisement();
            generalAdvertisement.setAdvertisement(adverts);
            generalAdvertisementApi.add("Bearer "+token1,generalAdvertisement).enqueue(new Callback<GeneralAdvertisement>() {
                @Override
                public void onResponse(Call<GeneralAdvertisement> call, Response<GeneralAdvertisement> response) {
                   advertisementAdapter.notifyDataSetChanged();
                    Toast.makeText(AddAdvertisementCustomer.this, "Dodano ogłoszenie", Toast.LENGTH_SHORT).show();}
                @Override
                public void onFailure(Call<GeneralAdvertisement> call, Throwable t) {
                    Toast.makeText(AddAdvertisementCustomer.this, "Nie dodano ogłoszenia", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(AddingProductsCustomerActivity.class.getName() ).log(Level.SEVERE,"Error ");}});
            Intent intent = new Intent(getApplicationContext(), AddAdvertisementCustomer.class);
            startActivity(intent);
            finish();
            }
        });}
    public void viewListProduct() {
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        RecyclerView recyclerAdvert = findViewById(R.id.recycle_advert);
        RetrofitServ retrofitServ = new RetrofitServ();
        GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerAdvert.setLayoutManager(linearLayoutManager);
        recyclerAdvert.addItemDecoration(new DividerItemDecoration(AddAdvertisementCustomer.this,
                DividerItemDecoration.VERTICAL));
        generalAdvertisementApi.getAll("Bearer "+token1).enqueue(new Callback<List<GeneralAdvertisement>>() {
            @Override
            public void onResponse(Call<List<GeneralAdvertisement>> call, Response<List<GeneralAdvertisement>> response) {
                if(response.isSuccessful()){
                    List<GeneralAdvertisement> advertResponse = response.body();
                    if(advertResponse !=null){
                        generalAdvertisements = advertResponse;
                        if(!generalAdvertisements.isEmpty()){
                            AdvertisementAdapter advertisementAdapter = new AdvertisementAdapter(generalAdvertisements,monStudylistener,true);
                            recyclerAdvert.setAdapter(advertisementAdapter);
                        }else {
                            System.out.println("Pusta lista");
                        }
                    }else {
                        System.out.println("Odpowiedź Api jest pusta");
                    }
                }else {
                    System.out.println("błąd odpowiedzi");
                }
                Toast.makeText(AddAdvertisementCustomer.this, "Pomyślnie zapisano", Toast.LENGTH_SHORT).show();}
            @Override
            public void onFailure(Call<List<GeneralAdvertisement>> call, Throwable t) {
                Toast.makeText(AddAdvertisementCustomer.this, "Wystąpił błąd", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Niepowodzenie");
            }
        }) ;
    }
    @Override
    public void onStudyClick(int position,long id) {
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        GeneralAdvertisement g = generalAdvertisements.get(position);
        Dialog dialog = new Dialog(AddAdvertisementCustomer.this);
        dialog.setContentView(R.layout.edit_advertisement);
        AppCompatButton buttonEditAdvert = dialog.findViewById(R.id.buttonEditAdvertisement);
        TextInputEditText advertEdit =dialog.findViewById(R.id.advertisemenEeditText);
        advertEdit.setText(g.getAdvertisement());
        buttonEditAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitServ retrofitServ = new RetrofitServ();
                GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
                String editAdvertText = String.valueOf(advertEdit.getText());
                generalAdvertisement = new GeneralAdvertisement();
                generalAdvertisement.setAdvertisement(editAdvertText);
                generalAdvertisementApi.editById("Bearer "+token1,id, generalAdvertisement).enqueue(new Callback<GeneralAdvertisement>() {
                    @Override
                    public void onResponse(Call<GeneralAdvertisement> call, Response<GeneralAdvertisement> response) {
                        Toast.makeText(AddAdvertisementCustomer.this, "Pomyślnie edytowano ogłoszenie", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AddAdvertisementCustomer.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<GeneralAdvertisement> call, Throwable t) {
                        Toast.makeText(AddAdvertisementCustomer.this, "Nie edytowano ogłoszenia", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(GeneralAdvertisement.class.getName()).log(Level.SEVERE, "Error ");
                    }});
            }
        });
        dialog.show();
    }
    @Override
    public void onStudyLongClick(int position, long id) {
      delete(position,id);
}
public void delete(int position, long id){
    SharedPreferences sp = getSharedPreferences("main",0);
    String token1 = sp.getString("token", null);
    GeneralAdvertisement generalAdvertisement = generalAdvertisements.get(position);
    System.out.println("gdghrhrh");
    AlertDialog.Builder builder = new AlertDialog.Builder(AddAdvertisementCustomer.this)
            .setTitle("Usuń ogłoszenie")
            .setIcon(R.drawable.delete_icon)
            .setMessage("Czy na pewno chcesz usunąć ogłoszenie ?")
            .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    RetrofitServ retrofitServ = new RetrofitServ();
                    GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
                    generalAdvertisementApi.deleteById("Bearer "+token1,Long.valueOf(id)).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            System.out.println("Usuwanie powiodło się");
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            System.out.println("Usuwanie nie powiodło się");
                        }
                    });
                    Intent intent = new Intent(getApplicationContext(), AddAdvertisementCustomer.class);
                    startActivity(intent);
                    finish();
                };
            })
                    .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
    builder.show();
}



}
