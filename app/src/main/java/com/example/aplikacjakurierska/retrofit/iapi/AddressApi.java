//package com.example.aplikacjakurierska.retrofit.iapi;
//
//import com.example.aplikacjakurierska.ActivityCustomer.AddingProductsCustomerActivity;
//import com.example.aplikacjakurierska.retrofit.model.Address;
//import com.example.aplikacjakurierska.retrofit.model.Product;
//
//import java.util.List;
//import java.util.Optional;
//
//import retrofit2.Call;
//import retrofit2.CallAdapter;
//import retrofit2.http.Body;
//import retrofit2.http.DELETE;
//import retrofit2.http.GET;
//import retrofit2.http.Header;
//import retrofit2.http.POST;
//import retrofit2.http.PUT;
//import retrofit2.http.Path;
//
//public interface AddressApi {
//
//    @POST("/address/add")
//    Call<Address> add(
////            @Header("Authorization")String token,
//            @Body Address address);
//    @GET("/address/get") Call<List<Address>> getAll(
////    @Header("Authorization")String token
//    );
//    @GET("/address/get/{id}")
//    Call<Optional<Address>> getById(
////            @Header("Authorization")String token,
//            @Path("id")Long id);
//    @PUT("/address/edit/{id}")
//    Call<Address> editById(
////            @Header("Authorization")String token,
//            @Path("id")Long id,@Body Address address);
//    @DELETE("/address/delete/{id}") ///może być źle
//    Call<Address> deleteById(
////            @Header("Authorization")String token,
//            @Path("id")Long id);
//}
