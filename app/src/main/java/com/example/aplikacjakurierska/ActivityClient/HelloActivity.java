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
private Button helloButton;
private Button productButton;
    Animation scaleUp,scaleDown;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
       moveAdvert();
       motiveButton();
    }


    public void moveAdvert(){
        helloButton =  findViewById(R.id.hello);
        SharedPreferences sp = getSharedPreferences("main", 0);
        String role2 = sp.getString("role",null);
        helloButton.setOnClickListener(new View.OnClickListener() {
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
        helloButton = findViewById(R.id.hello);
        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_button);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down_button);
        helloButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    helloButton.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    helloButton.startAnimation(scaleDown);
                }
                return false;
            }
        });}
}