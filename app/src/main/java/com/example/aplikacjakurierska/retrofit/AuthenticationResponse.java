package com.example.aplikacjakurierska.retrofit;

public class AuthenticationResponse {
    private String token;
    private String email;

    public AuthenticationResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthenticationResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

