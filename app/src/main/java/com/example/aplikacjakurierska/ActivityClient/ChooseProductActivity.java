package com.example.aplikacjakurierska.ActivityClient;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.PositionCustomerOrderApi;
import com.example.aplikacjakurierska.retrofit.iapi.ProductApi;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseProductActivity extends AppCompatActivity implements ChooseProductAdapter.OnStudyListener {
    private List<Product> chooseproductList;
    private List<PositionCustomerOrder> positionCustomerOrders = new ArrayList<>();

    //wszystkie pozycje które przechowywane są w tej lisćie dopóki użytkownik nie utworzy zamówienia

    private ChooseProductAdapter.OnStudyListener monStudylistener;
    Button backtoMainButton;
    private TextView nameChoose,priceChoose;
    private EditText descChoose;
    String id;
ChooseProductAdapter chooseProductAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);
        monStudylistener = this;
        chooseProductAdapter = new ChooseProductAdapter(new ArrayList<>(), monStudylistener);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseProductActivity.this, MainNewActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        viewListProduct();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.new_menu, menu);
        return super.onCreateOptionsMenu(menu);    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.historyordermenu) {
            Intent intent1 = new Intent(this,HistoryOrderActivity.class);
            this.startActivity(intent1);
            return true;
        }
        if (id == R.id.cartShop) {
            Intent intent2 = new Intent(this,OrderAcitivty.class);
            this.startActivity(intent2);
            return true;
        }
        if (id == R.id.shoppingcartmenu) {
            Intent intent2 = new Intent(this,OrderAcitivty.class);
            this.startActivity(intent2);
            return true;
        }

        if (id == R.id.logoutmenu) {
            SharedPreferences sharedPreferences = getSharedPreferences("main", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent2 = new Intent(this,LoginActivity.class);
            this.startActivity(intent2);
    }
        return super.onOptionsItemSelected(item);
    }
    public   void viewListProduct() {
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        System.out.println("jebaniutki token: " +token1);
        RecyclerView recyclertest = findViewById(R.id.recyclerViewClientProductChoose);
        RetrofitServ retrofitServ = new RetrofitServ();
        ProductApi productApi = retrofitServ.getRetrofit().create(ProductApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclertest.setLayoutManager(linearLayoutManager);

        productApi.getAll("Bearer "+ token1
                ).enqueue(new Callback<List<Product>>() {
            @Override
          public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                System.out.println(response.body());
                if(response.isSuccessful()){
                  TextView  emptyView = (TextView) findViewById(R.id.emptyList);
                    if (response.body().isEmpty()) {
                        recyclertest.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                    else {
                        recyclertest.setVisibility(View.VISIBLE);
                        emptyView.setText("WYBIERZ PRODUKT");
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    List<Product> productResponse = response.body();
                    System.out.println(productResponse);
                    if(productResponse !=null){
                        chooseproductList = productResponse;
                        if(!chooseproductList.isEmpty()){
                            chooseProductAdapter = new ChooseProductAdapter(chooseproductList,monStudylistener);
                            recyclertest.setAdapter(chooseProductAdapter);
                            chooseProductAdapter.notifyDataSetChanged();



                        }else {
                            System.out.println("Pusta lista");
                        }
                    }else {
                        System.out.println("odpowiddz Api jest pusta");
                    }
                }else {
                    System.out.println("błąd odpowiedzi");
                }
                Toast.makeText(ChooseProductActivity.this, "pomylsnie zapisano", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ChooseProductActivity.this, "Nie zapisano jest bład", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Błąd");
            }

        }) ;

    }

    @Override
    public void onStudyClick(int position) {
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        Long id = sp.getLong("id",0);
        Product clickedProduct = chooseproductList.get(position);
        String nameClicked = clickedProduct.getProductName();
        String priceClicked = clickedProduct.getProductPrice().toString();
        Double priceClickedFloat = clickedProduct.getProductPrice();
        Dialog dialog = new Dialog(ChooseProductActivity.this);
        dialog.setContentView(R.layout.choose_cart_client_dialog);
        AppCompatButton button = dialog.findViewById(R.id.buttonAddProductChoose);
        nameChoose = dialog.findViewById(R.id.nameProductChoose);
        priceChoose = dialog.findViewById(R.id.priceProductChoose);
        descChoose = dialog.findViewById(R.id.descriptionProductChoose);
        Editable descChooseText = descChoose.getText();
        nameChoose.setText(nameClicked);
        priceChoose.setText(priceClicked);

//        imageGallery = dialog.findViewById(R.id.galleryImage);
//        ImageView imageView = dialog.findViewById(R.id.galleryImage);
        NumberPicker numberPicker = dialog.findViewById(R.id.numberPickerChooseKG);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(300);
        numberPicker.setValue(1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitServ retrofitServ = new RetrofitServ();
                PositionCustomerOrderApi positionCustomerOrderApi = retrofitServ.getRetrofit().create(PositionCustomerOrderApi.class);
                double all = priceClickedFloat * numberPicker.getValue();
                PositionCustomerOrder positionCustomerOrder = new PositionCustomerOrder();
                positionCustomerOrder.setProduct(clickedProduct);
                positionCustomerOrder.setAmount(numberPicker.getValue());
                positionCustomerOrder.setPriceAll((float) all);
positionCustomerOrderApi.addPositionAndAddToCart( "Bearer "+ token1,positionCustomerOrder,id).enqueue(new Callback<Optional<PositionCustomerOrder>>() {
    @Override
    public void onResponse(Call<Optional<PositionCustomerOrder>> call, Response<Optional<PositionCustomerOrder>> response) {
        dialog.cancel();
        Toast.makeText(ChooseProductActivity.this, "Produkt dodano do koszyka", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<Optional<PositionCustomerOrder>> call, Throwable t) {
        Toast.makeText(ChooseProductActivity.this, "Produktu nie dodano do koszyka", Toast.LENGTH_SHORT).show();

    }
});



            }
        });
        dialog.show();

    }

    @Override
    public void onStudyLongClick(int position, long id) {

    }
}













