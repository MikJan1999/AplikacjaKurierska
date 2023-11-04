package com.example.aplikacjakurierska.retrofit.model;

public enum StatusOrder {
    CREATED("Złożone"),
    CONFIRM("Zatwierdzone"),
    CANCELED("Zakończone");

    String nazwa;
    StatusOrder(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
}
