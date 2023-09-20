package com.example.aplikacjakurierska.ActivityClient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.aplikacjakurierska.ActivityCustomer.AddAdvertisementCustomer;
import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
import com.example.aplikacjakurierska.R;

public class HelloActivity extends AppCompatActivity {
private Button advertButton;
private Button productButton;
    Animation scaleUp,scaleDown;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
       moveAdvert();
       moveProduct();
       motiveButton();
    }

    public void moveProduct(){
        productButton = findViewById(R.id.buttonMoveToProduct);
        SharedPreferences sp = getSharedPreferences("main", 0);
        String role2 = sp.getString("role",null);
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role2.equals("USER")){
                    Intent secondActivityIntent1 = new Intent(getApplicationContext(), ChooseProductActivity.class);
                    startActivity(secondActivityIntent1);
                }else if (role2.equals("ADMIN")){
                    Intent secondActivityIntent1 = new Intent(getApplicationContext(), AddingProductsCustomerActivity.class);
                    startActivity(secondActivityIntent1);
                }            }


        });

    }
    public void moveAdvert(){
        advertButton =  findViewById(R.id.buttonMoveToAdvert);
        SharedPreferences sp = getSharedPreferences("main", 0);
        String role2 = sp.getString("role",null);
        advertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role2.equals("USER")){
                    Intent secondActivityIntent1 = new Intent(getApplicationContext(), MainNewActivity.class);
                    startActivity(secondActivityIntent1);
                }else if (role2.equals("ADMIN")){
                    Intent secondActivityIntent1 = new Intent(getApplicationContext(), AddAdvertisementCustomer.class);
                    startActivity(secondActivityIntent1);
                }            }


        });

    }



    private void motiveButton(){
        advertButton = findViewById(R.id.hello);
        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_button);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down_button);
        advertButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    advertButton.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    advertButton.startAnimation(scaleDown);
                }
                return false;
            }
        });}
}