package com.example.aplikacjakurierska.model;

import android.hardware.lights.LightsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/addUser")
    Call<User> newUSer(@Body User user);

    @GET("/users")
    Call<List<User>> allUser();



}
