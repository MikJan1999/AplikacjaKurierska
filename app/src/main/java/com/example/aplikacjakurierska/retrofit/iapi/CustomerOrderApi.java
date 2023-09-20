package com.example.aplikacjakurierska.retrofit.iapi;

import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerOrderApi {
    @POST("/customer_order/create")
    Call<Void> createOrder(@Body CustomerOrder customerOrder);

    @GET("/customer_order/get_by_userId/{userId}")
    Call<List<CustomerOrder>>  findByUserId(@Path("userId")Long userId);

    @GET("/get")
     Call<List<CustomerOrder>> getAll();
}
