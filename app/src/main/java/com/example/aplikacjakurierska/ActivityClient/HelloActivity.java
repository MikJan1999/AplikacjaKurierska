package com.example.aplikacjakurierska.ActivityClient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.aplikacjakurierska.R;

public class HelloActivity extends AppCompatActivity {
private Button hello;
    Animation scaleUp,scaleDown;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        hello =  findViewById(R.id.hello);
        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HelloActivity.this, LoginActivity.class));
            }
        });
    }

    private void motiveButton(){
        hello = findViewById(R.id.hello);
        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_button);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down_button);
        hello.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    hello.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    hello.startAnimation(scaleDown);
                }
                return false;
            }
        });}
}