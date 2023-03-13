package com.example.aplikacjakurierska.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.model.RetrofitServ;
import com.example.aplikacjakurierska.model.User;
import com.example.aplikacjakurierska.model.UserApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
    }

    private void initializeComponents() {
       TextInputEditText editName = findViewById(R.id.editTextTextPersonName);
       TextInputEditText editUsername = findViewById(R.id.editTextTextPersonUsername);
        MaterialButton buttonSAve = findViewById(R.id.button);
RetrofitServ retrofitServ = new RetrofitServ();
        UserApi userApi = retrofitServ.getRetrofit().create(UserApi.class);
        buttonSAve.setOnClickListener(view -> {
            String name = String.valueOf(editName.getText());
            String username = String.valueOf(editUsername.getText());

            User user = new User();
            user.setName(name);
            user.setUsername(username);
userApi.newUSer(user)
        .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(LoginActivity.this, "Pomyślnie zapisano", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Nie zapisano", Toast.LENGTH_SHORT).show();
                Logger.getLogger(LoginActivity.this.getPackageName()).log(Level.SEVERE,"Error",t);
            }
        });


        });
    }
}