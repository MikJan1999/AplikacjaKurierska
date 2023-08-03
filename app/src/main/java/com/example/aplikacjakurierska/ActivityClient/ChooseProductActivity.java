package com.example.aplikacjakurierska.ActivityClient;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
import com.example.aplikacjakurierska.ActivityCustomer.ProductCustomerAdapter;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.iapi.ProductApi;
import com.example.aplikacjakurierska.retrofit.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseProductActivity extends AppCompatActivity implements ChooseProductAdapter.OnStudyListener {
    private List<Product> chooseproductList;
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
         backtoMainButton = (Button) findViewById(R.id.backToMin);
        viewListProduct();
        monStudylistener = this;
        chooseProductAdapter = new ChooseProductAdapter(new ArrayList<>(), monStudylistener);





        backtoMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseProductActivity.this,MainNewActivity.class));
            }
        });
    }

    public   void viewListProduct() {
        RecyclerView recyclertest = findViewById(R.id.recyclerViewClientProductChoose);
        RetrofitServ retrofitServ = new RetrofitServ();
        ProductApi productApi = retrofitServ.getRetrofit().create(ProductApi.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclertest.setLayoutManager(linearLayoutManager);

        productApi.getAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                System.out.println(chooseproductList + "Listaaaaaaaaaaaaa");
                if(response.isSuccessful()){
                    List<Product> productResponse = response.body();
                    if(productResponse !=null){
                        chooseproductList = productResponse;
                        if(!chooseproductList.isEmpty()){
                            chooseProductAdapter = new ChooseProductAdapter(chooseproductList,monStudylistener);
                            recyclertest.setAdapter(chooseProductAdapter);


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

        Product clickedProduct = chooseproductList.get(position);
        String nameClicked = clickedProduct.getProductName();
        String priceClicked = clickedProduct.getProductPrice().toString();
        Dialog dialog = new Dialog(ChooseProductActivity.this);
        dialog.setContentView(R.layout.choose_cart_client_dialog);
        AppCompatButton button = dialog.findViewById(R.id.buttonEditProductChoose);
        nameChoose =dialog.findViewById(R.id.nameProductChoose);
        priceChoose =dialog.findViewById(R.id.priceProductChoose);
        descChoose = dialog.findViewById(R.id.descriptionProductChoose);
        nameChoose.setText(nameClicked);
        priceChoose.setText(priceClicked);



//        imageGallery = dialog.findViewById(R.id.galleryImage);
//        ImageView imageView = dialog.findViewById(R.id.galleryImage);

        NumberPicker numberPicker = dialog.findViewById(R.id.numberPickerChooseKG);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(300);
        numberPicker.setValue(1);

        RetrofitServ retrofitServ = new RetrofitServ();
        ProductApi productApi = retrofitServ.getRetrofit().create(ProductApi.class);

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {



    }
});
dialog.show();

    }

    @Override
    public void onStudyLongClick(int position, long id) {

    }
}












