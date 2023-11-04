package com.example.aplikacjakurierska.ActivityClient;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.aplikacjakurierska.ActivityCustomer.AddAdvertisementCustomer;
import com.example.aplikacjakurierska.R;
import com.example.aplikacjakurierska.retrofit.AuthenticationResponse;
import com.example.aplikacjakurierska.retrofit.RegisterRequest;
import com.example.aplikacjakurierska.retrofit.RegisterRequestApi;
import com.example.aplikacjakurierska.retrofit.RetrofitServ;
import com.example.aplikacjakurierska.retrofit.Role;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLOutput;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registration();
        Animation scaleUp, scaleDown;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
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
    String repeatPasswords = String.valueOf(repeatPassword.getText());
    boolean ifs = passwords.equals(repeatPasswords);
    if (names.isEmpty() || lastNames.isEmpty() || emails.isEmpty() || passwords.isEmpty() || repeatPasswords.isEmpty() || ifs == false) {
        Toast.makeText(this, "Wprowadź prawidłowo wszystkie dane" , Toast.LENGTH_SHORT).show();

}else{RegisterRequest registerRequestAdd = new RegisterRequest();
        registerRequestAdd.setFirstName(names);
        registerRequestAdd.setLastName(lastNames);
        registerRequestAdd.setEmail(emails);
        registerRequestAdd.setPassword(repeatPasswords);
        registerRequestAdd.setRole(Role.USER);

        registerRequestApi.registerPerson(registerRequestAdd).enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                Toast.makeText(RegistrationActivity.this, "Rejestracja powiodła się", Toast.LENGTH_SHORT).show();
                String token = response.body().getToken();
                String email = response.body().getEmail();
                Long id = response.body().getId();
                String role = response.body().getRole();
                System.out.println(token + "        email: " + email + "id: "+ id);

                SharedPreferences sp = getSharedPreferences("main", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token", token);
                editor.putLong("id",id);
                editor.putString("role",role);
                editor.commit();
                String token1 = sp.getString("token", null);
                System.out.println("Token po rejestracji :  " + token1);
                String role2 = sp.getString("role",null);
                Intent secondActivityIntent1 = new Intent(getApplicationContext(), HelloActivity.class);
                startActivity(secondActivityIntent1);



            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Rejestracja nie powiodła się", Toast.LENGTH_SHORT).show();
            }
        });

    }
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

