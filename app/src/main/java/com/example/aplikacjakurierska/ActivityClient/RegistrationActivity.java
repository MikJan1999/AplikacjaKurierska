package com.example.aplikacjakurierska.ActivityClient;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.AuthenticationResponse;
import com.example.aplikacjakurierska.retrofit.RegisterRequest;
import com.example.aplikacjakurierska.retrofit.RegisterRequestApi;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.Role;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Animation scaleUp, scaleDown;
       registration();
    }
    MasterKey getMasterKey() {
        try {
            KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                    "_androidx_security_master_key_",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .build();

            return new MasterKey.Builder(this)
                    .setKeyGenParameterSpec(spec)
                    .build();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error on getting master key", e);
        }
        return null;
    }

    public SharedPreferences getEncryptedSharedPreferences() {
        try {
            return (SharedPreferences) EncryptedSharedPreferences.create(
                    Objects.requireNonNull(this),
                    "your",
                    getMasterKey(), // calling the method above for creating MasterKey
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error on getting encrypted shared preferences", e);
        }
        return null;
    }


    private void registration(){

        Button buttonRegister = findViewById(R.id.buttonRegister);
        TextInputEditText name = findViewById(R.id.editTextTextPersonName);
        TextInputEditText lastName = findViewById(R.id.editTextTextPersonLastName);
        TextInputEditText email = findViewById(R.id.editTextTextPersonEmail);
        TextInputEditText password = findViewById(R.id.editTextTextPersonPassword);
        TextInputEditText repeatPassword = findViewById(R.id.editTextTextPersonRepeatPassword);
        RetrofitServ retrofitServ = new RetrofitServ();
        RegisterRequestApi registerRequestApi = retrofitServ.getRetrofit().create(RegisterRequestApi.class);
buttonRegister.setOnClickListener(view -> {
    String names = String.valueOf(name.getText());
    String lastNames = String.valueOf(lastName.getText());
    String emails = String.valueOf(email.getText());
    String passwords = String.valueOf(password.getText());
    String  repeatPasswords = String.valueOf(repeatPassword.getText());

    RegisterRequest registerRequestAdd = new RegisterRequest();
    registerRequestAdd.setFirstName(names);
    registerRequestAdd.setLastName(lastNames);
    registerRequestAdd.setEmail(emails);
    registerRequestAdd.setRole(Role.USER);
    if (passwords.equals(repeatPasswords)){
        registerRequestAdd.setPassword(repeatPasswords);
    }

    registerRequestApi.registerPerson(registerRequestAdd).enqueue(new Callback<AuthenticationResponse>() {
        @Override
        public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
            Toast.makeText(RegistrationActivity.this, "Zajebiście", Toast.LENGTH_SHORT).show();
            String token = response.body().getToken();
            String email = response.body().getEmail();
            System.out.println(token + "        email: " + email) ;


            SharedPreferences sp = getSharedPreferences("main",0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("token",token);
            editor.commit();
            String token1 = sp.getString("token", null);
            System.out.println("Token po rejestracji :  "+ token1);
//
            openNewActivity();
        }

        @Override
        public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
            Toast.makeText(RegistrationActivity.this, "Chujowo", Toast.LENGTH_SHORT).show();
        }
    });

});


    }











    private void openNewActivity(){
        Button buttonSecondActivity = findViewById(R.id.buttonRegister);
        buttonSecondActivity.setOnClickListener(view -> {
            Intent secondActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(secondActivityIntent);
//            motiveButton();
        });

    }
//    private void motiveButton(){
//
//        buttonRegister = findViewById(R.id.buttonRegister);
//        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_button);
//        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down_button);
//        buttonRegister.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    buttonRegister.startAnimation(scaleUp);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    buttonRegister.startAnimation(scaleDown);
//                }
//                return false;
//            }
//        });}
}

