package com.example.aplikacjakurierska.retrofit;

public interface AuthCallback {
    void onSuccess();

    void onError(String errorMessage);
}
