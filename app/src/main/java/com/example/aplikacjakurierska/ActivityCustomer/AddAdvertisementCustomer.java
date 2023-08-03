package com.example.aplikacjakurierska.ActivityCustomer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    AdvertisementAdapter advertisementAdapter;
    AppCompatButton buttonAddAdvert;
    Long id;



    private AdvertisementAdapter.OnStudyListener monStudylistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_advertisement_customer);
        monStudylistener = this;
        AppCompatButton buttonAddAdvert = findViewById(R.id.buttonEditAdvertisement);


        addAdvertCustomer();
viewListProduct();
    }

    private void addAdvertCustomer() {
        TextInputEditText advert = findViewById(R.id.advertisementaddtext);

        RetrofitServ retrofitServ = new RetrofitServ();
        GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
         buttonAddAdvert = findViewById(R.id.buttonEditAdvertisement);

        buttonAddAdvert.setOnClickListener(view -> {
            String adverts = String.valueOf(advert.getText());
            String toString = advert.getText().toString();
//            Date date = advert.getText()
            if(toString.isEmpty()){
                Toast.makeText(AddAdvertisementCustomer.this, "Proszę wprowadzić tekst", Toast.LENGTH_SHORT).show();
            }
            else {


            GeneralAdvertisement generalAdvertisement = new GeneralAdvertisement();
            generalAdvertisement.setAdvertisement(adverts);
            generalAdvertisementApi.add(generalAdvertisement).enqueue(new Callback<GeneralAdvertisement>() {
                @Override
                public void onResponse(Call<GeneralAdvertisement> call, Response<GeneralAdvertisement> response) {
                    Toast.makeText(AddAdvertisementCustomer.this, "Pomyślnie zapisano produkt", Toast.LENGTH_SHORT).show();}
                @Override
                public void onFailure(Call<GeneralAdvertisement> call, Throwable t) {
                    Toast.makeText(AddAdvertisementCustomer.this, "Nie zapisano produktu", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(AddingProductsCustomerActivity.class.getName() ).log(Level.SEVERE,"Error ");}});
            Intent intent = new Intent(getApplicationContext(), AddAdvertisementCustomer.class);
            startActivity(intent);
            }


        });}


    public void viewListProduct() {
        RecyclerView recyclerAdvert = findViewById(R.id.recycle_advert);


        RetrofitServ retrofitServ = new RetrofitServ();
        GeneralAdvertisementApi generalAdvertisementApi = retrofitServ.getRetrofit().create(GeneralAdvertisementApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerAdvert.setLayoutManager(linearLayoutManager);
        recyclerAdvert.addItemDecoration(new DividerItemDecoration(AddAdvertisementCustomer.this,
                DividerItemDecoration.VERTICAL));


        generalAdvertisementApi.getAll().enqueue(new Callback<List<GeneralAdvertisement>>() {
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
                        System.out.println("odpowiddz Api jest pusta");
                    }
                }else {
                    System.out.println("błąd odpowiedzi");
                }
                Toast.makeText(AddAdvertisementCustomer.this, "Pomyślnie zapisano", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<GeneralAdvertisement>> call, Throwable t) {
                Toast.makeText(AddAdvertisementCustomer.this, "Wystąpił błąd", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Niepowodzenie");
            }
        }) ;

    }


    @Override
    public void onStudyClick(int position,long id) {
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

                generalAdvertisementApi.editById(id, generalAdvertisement).enqueue(new Callback<GeneralAdvertisement>() {

                    @Override
                    public void onResponse(Call<GeneralAdvertisement> call, Response<GeneralAdvertisement> response) {
                        Toast.makeText(AddAdvertisementCustomer.this, "Pomyślnie edytowano ogłoszenie", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onFailure(Call<GeneralAdvertisement> call, Throwable t) {
                        Toast.makeText(AddAdvertisementCustomer.this, "Nie edytowano ogłoszenia", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(GeneralAdvertisement.class.getName()).log(Level.SEVERE, "Error ");
                    }
                });

                Intent intent = new Intent(getApplicationContext(), AddAdvertisementCustomer.class);
                startActivity(intent);


            }
        });
        dialog.show();
    }



    @Override
    public void onStudyLongClick(int position, long id) {
      delete(position,id);
}
public void delete(int position, long id){
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

                    generalAdvertisementApi.deleteById(Long.valueOf(id)).enqueue(new Callback<Void>() {
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
