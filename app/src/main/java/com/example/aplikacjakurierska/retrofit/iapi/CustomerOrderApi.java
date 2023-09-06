package com.example.aplikacjakurierska.retrofit.iapi;

import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CustomerOrderApi {
    @POST("/pco/create")
    Call<Void> createOrder(@Body CustomerOrder customerOrder);

}
