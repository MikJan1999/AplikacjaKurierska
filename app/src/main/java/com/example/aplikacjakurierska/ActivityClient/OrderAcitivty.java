
package com.example.aplikacjakurierska.ActivityClient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.iapi.PositionCustomerOrderApi;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderAcitivty extends AppCompatActivity implements OrderAdapter.OnStudyListener {

private RecyclerView recyclerView;
    private OrderAdapter.OnStudyListener monStudylistener;
private  boolean isCartEmpty = true;
    Button pressToDelivery;
    private List<PositionCustomerOrderWithProductNameDTO> cartproductList;
    OrderAdapter orderAdapter;
RecyclerView recyclerCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        monStudylistener = this;
        recyclerView = findViewById(R.id.card_list_recycler);
        viewListProduct();

        pressToDelivery = (Button) findViewById(R.id.pressToDelivery);
        pressToDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println( "sprawdzenie "+ cartproductList);
//                if (isCartEmpty = true){
//                    Toast.makeText(OrderAcitivty.this, "Twój koszyk jest pusty", Toast.LENGTH_SHORT).show();
//                }else {
                    startActivity(new Intent(OrderAcitivty.this,OrderAdressActivity.class));
//                }
            }});
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderAcitivty.this, MainNewActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
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
    Long userId = sp.getLong("id",0);
    RecyclerView recyclerCard = findViewById(R.id.card_list_recycler);
    RetrofitServ retrofitServ = new RetrofitServ();
    PositionCustomerOrderApi positionCustomerOrderApi = retrofitServ.getRetrofit().create(PositionCustomerOrderApi.class);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
    recyclerCard.setLayoutManager(linearLayoutManager);

    positionCustomerOrderApi.getAllPositionCustomerOrdersWithProductNamesByUserId(
                "Bearer "+token1,
                userId
    ).enqueue(new Callback<List<PositionCustomerOrderWithProductNameDTO>>() {
        @Override
        public void onResponse(Call<List<PositionCustomerOrderWithProductNameDTO>> call,
                               Response<List<PositionCustomerOrderWithProductNameDTO>> response) {
            if(response.isSuccessful()){
                List<PositionCustomerOrderWithProductNameDTO> productResponse = response.body();
                if(productResponse !=null){
                    cartproductList = productResponse;
                    if(!cartproductList.isEmpty()){
                        orderAdapter = new OrderAdapter(cartproductList,monStudylistener);
                        recyclerCard.setAdapter(orderAdapter);
                        isCartEmpty = false;

                    }else {
                        System.out.println("Pusta lista");
                    }
                }else {
                    System.out.println("odpowiddz Api jest pusta");
                }
            }else {
                System.out.println("błąd odpowiedzi");
            }
            Toast.makeText(OrderAcitivty.this, "pomylsnie zapisano", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<List<PositionCustomerOrderWithProductNameDTO>> call, Throwable t) {
            Toast.makeText(OrderAcitivty.this, "Nie zapisano jest bład", Toast.LENGTH_SHORT).show();
            Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Błąd");
        }

    }) ;

}


    @Override
    public void onStudyClick(int position) {

    }

    @Override
    public void onStudyLongClick(int position, long id) {

        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);

        PositionCustomerOrderWithProductNameDTO positionCustomerOrderWithProductNameDTO = cartproductList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderAcitivty.this)
                .setTitle("Usuń produkt")
                .setIcon(R.drawable.delete_icon)
                .setMessage("Czy na pewno usunąć ten produkt ?")
                .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RetrofitServ retrofitServ = new RetrofitServ();
                        PositionCustomerOrderApi positionCustomerOrderApi = retrofitServ.getRetrofit().create(PositionCustomerOrderApi.class);

                        positionCustomerOrderApi.deleteById(
                                "Bearer "+token1,
                                Long.valueOf(id)).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                System.out.println("git");
                                System.out.println(cartproductList + "cartListprzed");
                                cartproductList.remove(position);
                                System.out.println(cartproductList + "cartList");
                                orderAdapter.notifyDataSetChanged();
                                }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }});};})
                .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }});
        builder.show();}



    }
