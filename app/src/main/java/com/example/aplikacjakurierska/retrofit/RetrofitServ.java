package com.example.aplikacjakurierska.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServ {




private Retrofit retrofit;

public RetrofitServ(){
initializeRetrofit();
}
private void  initializeRetrofit(){


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();
    retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.17:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
