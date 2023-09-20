package com.example.aplikacjakurierska.retrofit.iapi;



import com.example.aplikacjakurierska.retrofit.model.CartShop;
import com.example.aplikacjakurierska.retrofit.model.GeneralAdvertisement;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrder;

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

public interface CartShopApi {
@POST("/cart_shop/add")
Call<CartShop> add(
//        @Header("Authorization")String token,
        @Body CartShop cartShop);
    @GET("/cart_shop/get")
    Call<List<CartShop>> getAll(
//            @Header("Authorization")String token
    );
    @GET("/cart_shop/get/{id}")
    Call<CartShop> getById(
//            @Header("Authorization")String token,
            @Path("id")Long id);
    @PUT("/cart_shop/edit/{id}")
    Call<GeneralAdvertisement> editById(
//            @Header("Authorization")String token,
            @Path("id")Long id,@Body GeneralAdvertisement generalAdvertisement);
    @DELETE("/cart_shop/delete/{id}")Call<Void>deleteById(
//            @Header("Authorization")String token,
            @Path("id")Long id);

    @POST("/cart_shop/{cartId}/add")
Call <String> addProductToCart(
//        @Header("Authorization")String token,
        @Path("cartId") Long cartId, @Body PositionCustomerOrder positionCustomerOrder);


}
