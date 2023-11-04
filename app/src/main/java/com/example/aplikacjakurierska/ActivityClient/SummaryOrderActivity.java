package com.example.aplikacjakurierska.ActivityClient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.PositionCustomerOrderApi;
import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;
import com.example.aplikacjakurierska.retrofit.model.StatusOrder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummaryOrderActivity extends AppCompatActivity implements OrderAdapter.OnStudyListener {
    private List<PositionCustomerOrderWithProductNameDTO> cartproductList;
    private OrderAdapter.OnStudyListener monStudylistener;
    private RecyclerView recyclerView;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_order);
        monStudylistener = this;
        recyclerView = findViewById(R.id.card_list_recycler);
viewSummaryOrder();

createOrder();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryOrderActivity.this, OrderAdressActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

    }

private void viewSummaryOrder(){
    SharedPreferences sp = getSharedPreferences("main",0);
    String token1 = sp.getString("token", null);
    Long userId = sp.getLong("id",0);

    RetrofitServ retrofitServ = new RetrofitServ();
    PositionCustomerOrderApi positionCustomerOrderApi = retrofitServ.getRetrofit().create(PositionCustomerOrderApi.class);
    RecyclerView recyclerCard = findViewById(R.id.card_list_recycler);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
    recyclerCard.setLayoutManager(linearLayoutManager);
    positionCustomerOrderApi.getAllPositionCustomerOrdersWithProductNamesByUserId("Bearer "+token1,userId).enqueue(new Callback<List<PositionCustomerOrderWithProductNameDTO>>() {
        @Override
        public void onResponse(Call<List<PositionCustomerOrderWithProductNameDTO>> call, Response<List<PositionCustomerOrderWithProductNameDTO>> response) {
            if(response.isSuccessful()){
                List<PositionCustomerOrderWithProductNameDTO> productResponse = response.body();
                if(productResponse !=null){
                    cartproductList = productResponse;
                    if(!cartproductList.isEmpty()){
                        orderAdapter = new OrderAdapter(cartproductList,monStudylistener);
                        recyclerCard.setAdapter(orderAdapter);


                    }else {
                        System.out.println("Pusta lista");
                    }
                }else {
                    System.out.println("odpowiddz Api jest pusta");
                }
            }else {
                System.out.println("błąd odpowiedzi");
            }
            Toast.makeText(SummaryOrderActivity.this, "pomylsnie zapisano", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<List<PositionCustomerOrderWithProductNameDTO>> call, Throwable t) {
            Toast.makeText(SummaryOrderActivity.this, "Nie zapisano jest bład", Toast.LENGTH_SHORT).show();

        }
    });
}
private void createOrder(){
    SharedPreferences sp = getSharedPreferences("main",0);
    String token1 = sp.getString("token", null);
    Long userId = sp.getLong("id",0);

    Button createOrderButton = findViewById(R.id.createOrder);
    TextInputEditText descOrder = findViewById(R.id.descOrder);
    TextView priceOrders =findViewById(R.id.summaryPriceOrder);
    TextView nameLastNames = findViewById(R.id.nameLastNameSummaryOrder);
    TextView streets = findViewById(R.id.streetSummaryOrder);
    TextView   numberHouses = findViewById(R.id.numberHouseSummaryOrder);
    TextView   villages = findViewById(R.id.villageSummaryOrder);
    TextView   phoneNumbers = findViewById(R.id.numberPhoneSummaryOrder);

    Intent receivedIntent = getIntent();
    String nameLastName = receivedIntent.getStringExtra("nameLastName");
    String street = receivedIntent.getStringExtra("street");
    String numberHouse = receivedIntent.getStringExtra("numberHouse");
    String village = receivedIntent.getStringExtra("village");
    String phoneNumber = receivedIntent.getStringExtra("nummberPhone");
    Float priceOrder = receivedIntent.getFloatExtra("priceOrder",0.0f);

    nameLastNames.setText(nameLastName);
    streets.setText(street);
    numberHouses.setText(numberHouse);
    villages.setText(village);
    phoneNumbers.setText(phoneNumber);
    priceOrders.setText(String.valueOf(priceOrder));


            createOrderButton.setOnClickListener(x-> {
    RetrofitServ retrofitServ = new RetrofitServ();
    PositionCustomerOrderApi positionCustomerOrderApi = retrofitServ.getRetrofit().create(PositionCustomerOrderApi.class);
    CustomerOrder newCustomer = new CustomerOrder();
    newCustomer.setNameAndSurname(nameLastName);
    newCustomer.setStreet(street);
    newCustomer.setVillage(village);
    newCustomer.setNumberOfPhone(Integer.parseInt(phoneNumber));
    newCustomer.setNumberOfHouse(numberHouse);
    newCustomer.setPriceOrder(priceOrder);
    newCustomer.setStatusOrder(StatusOrder.CREATED);
    newCustomer.setDescription(descOrder.getText().toString());


    positionCustomerOrderApi.createOrder("Bearer "+token1,newCustomer,userId).enqueue(new Callback<List<PositionCustomerOrder>>() {
        @Override
        public void onResponse(Call<List<PositionCustomerOrder>> call, Response<List<PositionCustomerOrder>> response) {
            Intent intent2 = new Intent(SummaryOrderActivity.this,ChooseProductActivity.class);
           startActivity(intent2);
           finish();
            Toast.makeText(SummaryOrderActivity.this, "Zamówienie zostało złożone", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailure(Call<List<PositionCustomerOrder>> call, Throwable t) {
            Toast.makeText(SummaryOrderActivity.this, "Nie udało się złożyć zamówienia", Toast.LENGTH_SHORT).show();
        }
    });

    });




}







    @Override
    public void onStudyClick(int position) {

    }

    @Override
    public void onStudyLongClick(int position, long id) {

    }
}
