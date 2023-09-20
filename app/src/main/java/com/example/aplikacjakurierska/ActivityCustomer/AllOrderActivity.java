package com.example.aplikacjakurierska.ActivityCustomer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityClient.HistoryOrderActivity;
import com.example.aplikacjakurierska.ActivityClient.HistoryOrderAdapter;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.CustomerOrderApi;
import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllOrderActivity extends AppCompatActivity {
    private List<CustomerOrder> historyAllCustomerOrderLists = new ArrayList<>();
    AllOrderAdapter allOrderAdapter;
    private AllOrderAdapter.OnStudyListener monStudylistenerHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_order_customer);
viewListProduct();
    }




    public   void viewListProduct() {
//        SharedPreferences sp = getSharedPreferences("main",0);
//        String token1 = sp.getString("token", null);
//        Long userId = sp.getLong("id",0);
        RecyclerView recyclerCard = findViewById(R.id.history_all_order_list_recycler);
        RetrofitServ retrofitServ = new RetrofitServ();
        CustomerOrderApi customerOrderApi = retrofitServ.getRetrofit().create(CustomerOrderApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerCard.setLayoutManager(linearLayoutManager);

        customerOrderApi.getAll(
//                "Bearer "+token1,

        ).enqueue(new Callback<List<CustomerOrder>>() {
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

                            System.out.println("GÓWNO");


                        }else {
                            System.out.println("Pusta lista");
                        }
                    }else {
                        System.out.println("odpowiddz Api jest pusta");
                    }
                }else {
                    System.out.println("błąd odpowiedzi");
                }
                Toast.makeText(HistoryOrderActivity.this, "pomylsnie zapisano", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CustomerOrder>> call, Throwable t) {
                Toast.makeText(HistoryOrderActivity.this, "Nie zapisano jest bład", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE,"Błąd");
            }

        }) ;

    }
}
