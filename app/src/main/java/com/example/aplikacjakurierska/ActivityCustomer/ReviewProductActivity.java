package com.example.aplikacjakurierska.ActivityCustomer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
public class ReviewProductActivity extends AppCompatActivity  implements ProductCustomerAdapter.OnStudyListener {

    Product productadd;
    private List<Product> productList;
    private TextView name,price,desc;
    String productName,productPrice,productDescription,createdAt,id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.review_product_activity);

        super.onCreate(savedInstanceState);
        Button backToMain = findViewById(R.id.backToMain);
        editProduct();
        name = findViewById(R.id.nameProductAddEdit);
        price = findViewById(R.id.priceProductEdit);
        desc = findViewById(R.id.descriptionProductEdit);
        Intent intent = getIntent();
        productName = intent.getStringExtra("productName");
        productPrice = intent.getStringExtra("productPrice");
        productDescription = intent.getStringExtra("productDescription");
        id = String.valueOf(intent.getLongExtra("id", 0L));
        name.setText(productName);
        price.setText(productPrice);
        desc.setText(productDescription);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }});}
    private void editProduct() {
        Button buttonEdit = findViewById(R.id.buttonEditProductForSaleEdit);
        RetrofitServ retrofitServ = new RetrofitServ();
        ProductApi productApi = retrofitServ.getRetrofit().create(ProductApi.class);
        buttonEdit.setOnClickListener(views -> {
            String nameproduct = String.valueOf(name.getText());
            String priceproduct = String.valueOf(price.getText());
            String descriptionproduct = String.valueOf(desc.getText());
            productadd = new Product();
            productadd.setProductName(nameproduct);
            productadd.setProductPrice(Double.valueOf(priceproduct));
            productadd.setProductDescription(descriptionproduct);
            productApi.editById(Long.valueOf(id), productadd).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    Toast.makeText(ReviewProductActivity.this, "Pomyślnie edytowano produkt", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Toast.makeText(ReviewProductActivity.this, "Nie edytowano produktu", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(AddingProductsCustomerActivity.class.getName()).log(Level.SEVERE, "Error ");
                }

            });


        });


    }

    @Override
    public void onStudyClick(int position) {
        Product p = productList.get(position);
        Intent intent = new Intent(getApplicationContext(), AddingProductsCustomerActivity.class);
        intent.putExtra("productName",p.getProductName());
        intent.putExtra("productPrice",p.getProductPrice().toString());
        intent.putExtra("productDescription",p.getProductDescription());
        intent.putExtra("id",p.getId());
        startActivity(intent);
    }
}















