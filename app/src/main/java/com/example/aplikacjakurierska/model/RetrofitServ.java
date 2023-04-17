package com.example.aplikacjakurierska.model;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServ {




private Retrofit retrofit;

public RetrofitServ(){
initializeRetrofit();
}
private void  initializeRetrofit(){

    retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.227:8070")
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();
}

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
