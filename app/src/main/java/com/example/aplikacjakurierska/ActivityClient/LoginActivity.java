package com.example.aplikacjakurierska.ActivityClient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikacjakurierska.ActivityCustomer.AddAdvertisementCustomer;
import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.AuthenticationRequest;
import com.example.aplikacjakurierska.retrofit.AuthenticationResponse;
import com.example.aplikacjakurierska.retrofit.RegisterRequest;
import com.example.aplikacjakurierska.retrofit.RegisterRequestApi;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.google.android.material.textfield.TextInputEditText;

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
login();
moveToRegister();

    }
public void moveToRegister(){
    TextView textViewRegister = findViewById(R.id.textViewRegister);
    textViewRegister.setOnClickListener(view -> {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), RegistrationActivity.class
        );
        startActivity(secondActivityIntent);
    });
}

    private void login(){
        Button buttonLogin = findViewById(R.id.buttonLoginCredentials);
        TextInputEditText email = findViewById(R.id.editTextTextPersonName);
        TextInputEditText password = findViewById(R.id.editTextTextPersonPassword);
        RetrofitServ retrofitServ = new RetrofitServ();
        RegisterRequestApi registerRequestApi = retrofitServ.getRetrofit().create(RegisterRequestApi.class);
buttonLogin.setOnClickListener(view -> {
    String emails = String.valueOf(email.getText());
    String passwords = String.valueOf(password.getText());
    if(emails.isEmpty() || passwords.isEmpty()){
        Toast.makeText(this, "Wprowadź wszystkie dane", Toast.LENGTH_SHORT).show();
    }else{
    AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    authenticationRequest.setEmail(emails);
    authenticationRequest.setPassword(passwords);
        registerRequestApi.authenticate(authenticationRequest).enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                Toast.makeText(LoginActivity.this, "Logowanie powiodło się", Toast.LENGTH_SHORT).show();
                String token = response.body().getToken();
                String role = response.body().getRole();
                Long id = response.body().getId();
                SharedPreferences sp = getSharedPreferences("main",0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token",token);
                editor.putString("role",role);
                editor.putLong("id",id);
                editor.commit();
                String role2 = sp.getString("role",null);
                String token2 = sp.getString("token", null);
                System.out.println( "Token po zalogowaniu:  "+ token2);
                System.out.println( "Rola po zalogowaniu:  "+ role2);
                Intent secondActivityIntent1 = new Intent(getApplicationContext(), HelloActivity.class);
                startActivity(secondActivityIntent1);
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Logowanie nie powiodło się", Toast.LENGTH_SHORT).show();

            }
        });
    }
});
    }
private void motiveButton(){

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


