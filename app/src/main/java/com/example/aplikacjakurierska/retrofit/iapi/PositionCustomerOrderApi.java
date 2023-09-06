package com.example.aplikacjakurierska.retrofit.iapi;

import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.GeneralAdvertisement;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrder;

import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PositionCustomerOrderApi {

    @POST("/pco/add")
    Call<PositionCustomerOrder> add(@Body PositionCustomerOrder positionCustomerOrder);
    @GET("/pco/get")
    Call<List<PositionCustomerOrder>> getAll();
    @GET("/pco/get/{id}")
    Call<Optional<PositionCustomerOrder>> getById(@Path("id")Long id);
    @PUT("/pco/edit/{id}")
    Call<PositionCustomerOrder> editById(@Path("id")Long id,@Body PositionCustomerOrder positionCustomerOrder);
    @DELETE("/pco/delete/{id}")Call<Void>deleteById(@Path("id")Long id);

}
