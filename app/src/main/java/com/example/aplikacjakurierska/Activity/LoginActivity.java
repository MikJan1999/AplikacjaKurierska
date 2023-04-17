package com.example.aplikacjakurierska.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.model.RetrofitServ;
import com.example.aplikacjakurierska.model.User;
import com.example.aplikacjakurierska.model.UserApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


