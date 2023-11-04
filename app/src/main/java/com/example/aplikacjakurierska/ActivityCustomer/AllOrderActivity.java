package com.example.aplikacjakurierska.ActivityCustomer;
import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityClient.ChooseProductActivity;
import com.example.aplikacjakurierska.ActivityClient.ChooseProductAdapter;
import com.example.aplikacjakurierska.ActivityClient.DialoghistoryAdapter;
import com.example.aplikacjakurierska.ActivityClient.LoginActivity;
import com.example.aplikacjakurierska.ActivityClient.OrderAcitivty;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.CustomerOrderApi;
import com.example.aplikacjakurierska.retrofit.iapi.PositionCustomerOrderApi;
import com.example.aplikacjakurierska.retrofit.iapi.ProductApi;
import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;
import com.example.aplikacjakurierska.retrofit.model.Product;
import com.example.aplikacjakurierska.retrofit.model.StatusOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllOrderActivity extends AppCompatActivity implements AllOrderAdapter.OnStudyListener{
    private List<CustomerOrder> historyAllCustomerOrderLists = new ArrayList<>();
    private List<PositionCustomerOrderWithProductNameDTO> positionCustomerOrderWithProductNameDTOList = new ArrayList<>();
    AllOrderAdapter allOrderAdapter;
    private AllOrderAdapter.OnStudyListener monStudylistenerHistory;
    PositionOfOrderAdapter positionOfOrderAdapter;
    Dialog dialog;
    RecyclerView dialogRecyclerView;
String select;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_order_customer);
        Toast.makeText(this, "Przytrzymaj aby zmienić status", Toast.LENGTH_SHORT).show();
monStudylistenerHistory = this;
allOrderAdapter = new AllOrderAdapter(historyAllCustomerOrderLists,monStudylistenerHistory);
positionOfOrderAdapter = new PositionOfOrderAdapter(positionCustomerOrderWithProductNameDTOList);
viewListProduct();
toolbarInitialize();

    }






    public void toolbarInitialize(){
    Toolbar toolbar = findViewById(R.id.toolbarBack);
    toolbar.setTitle("Lista zamówień");
    setSupportActionBar(toolbar);
    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    setSupportActionBar(toolbar);
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

    public   void viewListProduct() {
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        RecyclerView recyclerCard = findViewById(R.id.history_all_order_list_recycler);
        RetrofitServ retrofitServ = new RetrofitServ();
        CustomerOrderApi customerOrderApi = retrofitServ.getRetrofit().create(CustomerOrderApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerCard.setLayoutManager(linearLayoutManager);
        customerOrderApi.getAll("Bearer "+token1).enqueue(new Callback<List<CustomerOrder>>() {
            @Override
            public void onResponse(Call<List<CustomerOrder>> call,
                                   Response<List<CustomerOrder>> response) {
                if(response.isSuccessful()){
                    List<CustomerOrder> productResponse = response.body();
                    if(productResponse !=null){
                        historyAllCustomerOrderLists = productResponse;
                        System.out.println(historyAllCustomerOrderLists);
                        if(!historyAllCustomerOrderLists.isEmpty()){
                            allOrderAdapter    = new AllOrderAdapter(historyAllCustomerOrderLists, monStudylistenerHistory);
                            recyclerCard.setAdapter(allOrderAdapter);
                        }else {
                            System.out.println("Pusta lista");
                        }
                    }else {
                        System.out.println("odpowiddz Api jest pusta");
                    }
                }else {
                    System.out.println("Błąd odpowiedzi");
                }
            }

            @Override
            public void onFailure(Call<List<CustomerOrder>> call, Throwable t) {
                Toast.makeText(AllOrderActivity.this, "Nie zapisano jest bład", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Błąd");
            }
        }) ;
    }
    @Override
    public void onStudyClick(int position) {
         dialog = new Dialog(AllOrderActivity.this);
        dialog.setContentView(R.layout.preview_history_order);
        dialogRecyclerView = dialog.findViewById(R.id.history_position_list_recycler);
        System.out.println("nacisnieta pozycja " + position );
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        Long customerOrderid = historyAllCustomerOrderLists.get(position).getId();
        System.out.println(customerOrderid);
        RetrofitServ retrofitServ = new RetrofitServ();
        PositionCustomerOrderApi positionCustomerOrderApi = retrofitServ.getRetrofit().create(PositionCustomerOrderApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        dialogRecyclerView.setLayoutManager(linearLayoutManager);
        Button button = dialog.findViewById(R.id.buttonHistoryBack);
        button.setOnClickListener(x->{
            dialog.dismiss();
        });
        positionCustomerOrderApi.getAllPositionCustomerOrdersWithProductNamesByOrderId("Bearer "+token1,customerOrderid).enqueue(new Callback<List<PositionCustomerOrderWithProductNameDTO>>() {
           @Override
           public void onResponse(Call<List<PositionCustomerOrderWithProductNameDTO>> call, Response<List<PositionCustomerOrderWithProductNameDTO>> response) {
               if(response.isSuccessful()){
                   if (response.body().isEmpty()){
                       System.out.println("pusta lista");
                   }
                   List<PositionCustomerOrderWithProductNameDTO> positionResponse = response.body();
                   System.out.println(positionResponse);
                   if(positionResponse !=null){
                       positionCustomerOrderWithProductNameDTOList = positionResponse;
                       if(!positionCustomerOrderWithProductNameDTOList.isEmpty()){
                           positionOfOrderAdapter = new PositionOfOrderAdapter(positionCustomerOrderWithProductNameDTOList);
                           dialogRecyclerView.setAdapter(positionOfOrderAdapter);
                           positionOfOrderAdapter.notifyDataSetChanged();
                       }else {
                           System.out.println("Pusta lista");
                       }
                   }else {
                       System.out.println("odpowiddz Api jest pusta");
                   }
               }else {
                   System.out.println("błąd odpowiedzi");
               }
               Toast.makeText(AllOrderActivity.this, "pomylsnie zapisano", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<List<PositionCustomerOrderWithProductNameDTO>> call, Throwable t) {
           }

       });
       dialog.show();
    }
    @Override
    public void onStudyLongClick(int position, long id) {
        SharedPreferences sp = getSharedPreferences("main",0);
        String token1 = sp.getString("token", null);
        Long userId = sp.getLong("id",0);
        System.out.println("nacisnieta pozycja " + (position ));
        CustomerOrder customerOrder = historyAllCustomerOrderLists.get(position);

        final String[] options = {"Złożone", "Zatwierdzone", "Zakończone"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AllOrderActivity.this)
                .setTitle("ZMIANA STATUSU ZAMÓWIENIA")
                .setIcon(R.drawable.delete_icon)
                .setMessage("Wybierz status zamówienia");
        final RadioGroup radioGroup = new RadioGroup(this);
        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[i]);
            radioGroup.addView(radioButton);

            if (i == 0) {
                radioButton.setChecked(true);
            }
        }
        builder.setView(radioGroup);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = radioGroup.findViewById(selectedRadioButtonId);

                RetrofitServ retrofitServ = new RetrofitServ();
                CustomerOrderApi customerOrderApi = retrofitServ.getRetrofit().create(CustomerOrderApi.class);
                // Tutaj możesz uzyskać wybraną opcję
                if (selectedRadioButton != null) {
                    select = selectedRadioButton.getText().toString();
                    // Wykonaj działania na podstawie wybranej opcji
                    Toast.makeText(getApplicationContext(), "Wybrano: " + select, Toast.LENGTH_SHORT).show();
                    if (select.equals("Złożone")) {
                        customerOrder.setStatusOrder(StatusOrder.CREATED);

                    } else if (select.equals("Zatwierdzone")) {
                        customerOrder.setStatusOrder(StatusOrder.CONFIRM);
                    } else if (select.equals("Zakończone")) {
                        customerOrder.setStatusOrder(StatusOrder.CANCELED);

                    }
                    String dataCreateOrder = String.valueOf(customerOrder.getDataCreateOrder());


                    customerOrderApi.editById("Bearer " +token1,id,customerOrder).enqueue(new Callback<CustomerOrder>() {
                    @Override
                    public void onResponse(Call<CustomerOrder> call, Response<CustomerOrder> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(AllOrderActivity.this, "Status zmieniono na "+ selectedRadioButton, Toast.LENGTH_SHORT).show();
                            allOrderAdapter.notifyDataSetChanged();

                        }else {System.out.println("Problem z odpowiedzią HTTP");}


                    }

                    @Override
                    public void onFailure(Call<CustomerOrder> call, Throwable t) {
                        Toast.makeText(AllOrderActivity.this, "Zmiana statusu nie powiodła się", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            } });

// Utworzenie AlertDialog

        AlertDialog alertDialog = builder.create();
alertDialog.show();

    }

    }


