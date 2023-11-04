package com.example.aplikacjakurierska.retrofit.iapi;

import com.example.aplikacjakurierska.retrofit.model.CustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrder;
import com.example.aplikacjakurierska.retrofit.model.PositionCustomerOrderWithProductNameDTO;

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

public interface PositionCustomerOrderApi {

    @POST("/pco/add")
    Call<PositionCustomerOrder> add(
            @Header("Authorization")String token,
            @Body PositionCustomerOrder positionCustomerOrder);

    @GET("/pco/get")
    Call<List<PositionCustomerOrder>> getAll(
            @Header("Authorization")String token
    );

    @GET("/pco/get/{id}")
    Call<Optional<PositionCustomerOrder>> getById(
            @Header("Authorization")String token,
            @Path("id") Long id);

    @PUT("/pco/edit/{id}")
    Call<PositionCustomerOrder> editById(
            @Header("Authorization")String token,
            @Path("id") Long id, @Body PositionCustomerOrder positionCustomerOrder);

    @DELETE("/pco/delete/{id}")
    Call<Void> deleteById(
            @Header("Authorization")String token,
            @Path("id") Long id);

    @POST("/pco/add_and_add/{userId}")
    Call<Optional<PositionCustomerOrder>> addPositionAndAddToCart(@Header("Authorization")String token,
            @Body PositionCustomerOrder positionCustomerOrder,
            @Path("userId") Long userId);


    @GET("/pco/alll/{userId}")
    Call<List<PositionCustomerOrderWithProductNameDTO>> getAllPositionCustomerOrdersWithProductNamesByUserId
            (@Header("Authorization")String token,@Path("userId") Long userId);

    @GET("/pco/get_all_by_cart_shop_id/{cartShopId}")
    Call<List<PositionCustomerOrder>> getByCartShopId(
            @Header("Authorization")String token,@Path("cartShopId") Long cartShopId);

    @POST("/pco/create_order/{userId}")
    Call<List<PositionCustomerOrder>> createOrder(@Header("Authorization")String token,
                                                  @Body CustomerOrder customerOrder, @Path("userId") Long userId);

    @GET("/pco/get_by_co/{id}")
    Call<List<PositionCustomerOrderWithProductNameDTO>> getByCustomerOrderId(@Header("Authorization")String token,
                                                                             @Path("id") Long id);

    @GET("/pco/history_order_by_customer_order/{customer_orderId}")
    Call<List<PositionCustomerOrderWithProductNameDTO>> getAllPositionCustomerOrdersWithProductNamesByOrderId
            (@Header("Authorization")String token,
             @Path("customer_orderId") Long customer_orderId);


    }

