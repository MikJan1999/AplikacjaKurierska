package com.example.aplikacjakurierska.retrofit;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterRequestApi {

    @POST("/login/register")
    Call<AuthenticationResponse> registerPerson(@Body RegisterRequest registerRequest);
@POST("/login/authenticate")
Call<AuthenticationResponse> authenticate(@Body AuthenticationRequest authenticationRequest);
}
