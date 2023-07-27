package com.example.aplikacjakurierska.ActivityClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.aplikacjakurierska.R;

public class LoginActivity extends AppCompatActivity {
private Button buttonlogin;
Animation scaleUp,scaleDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       openNewActivity();
    }

private void openNewActivity(){
    Button buttonSecondActivity = findViewById(
            R.id.buttonloggin
    );
    buttonSecondActivity.setOnClickListener(view -> {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), MainNewActivity.class
        );
        startActivity(secondActivityIntent);
        motiveButton();
    });

}

private void motiveButton(){
    buttonlogin = findViewById(R.id.buttonloggin);
    scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_button);
    scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down_button);
    buttonlogin.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                buttonlogin.startAnimation(scaleUp);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                buttonlogin.startAnimation(scaleDown);
            }
            return false;
        }
    });}
    }


