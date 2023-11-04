package com.example.aplikacjakurierska.retrofit.iapi;

import com.example.aplikacjakurierska.retrofit.model.Product;

import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductApi {

    @POST("/product/add") Call<Product> add(
            @Header("Authorization")String token,
            @Body Product product);
    @GET("/product/get") Call<List<Product>> getAll(
            @Header("Authorization")String token
           );
    @GET("/product/get/{id}") Call<Optional<Product>> getById(@Path("id")Long id);
    @PUT("/product/edit/{id}") Call<Product> editById(
            @Header("Authorization")String token,
            @Path("id")Long id,
            @Body Product product);
    @DELETE("/product/delete/{id}")Call<Void>deleteById(
            @Header("Authorization")String token,
            @Path("id")Long id);
}
