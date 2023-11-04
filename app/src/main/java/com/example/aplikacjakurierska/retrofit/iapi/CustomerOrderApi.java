package com.example.aplikacjakurierska.retrofit.iapi;

import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustomerOrderApi {
    @POST("/customer_order/create")
    Call<Void> createOrder(@Header("Authorization")String token, @Body CustomerOrder customerOrder);

    @GET("/customer_order/get_by_userId/{userId}")
    Call<List<CustomerOrder>>  findByUserId(@Header("Authorization")String token,@Path("userId")Long userId);

    @GET("/customer_order/get")
     Call<List<CustomerOrder>> getAll(@Header("Authorization")String token);


    @PUT("/customer_order/edit/{id}")
    Call<CustomerOrder> editById(@Header("Authorization")String token,@Path("id") Long id, @Body CustomerOrder customerOrderEdit) ;

}
