package com.example.aplikacjakurierska.retrofit.model;

import com.example.aplikacjakurierska.ActivityCustomer.AdvertisementAdapter;

import java.util.ArrayList;
import java.util.Date;

public class GeneralAdvertisement {

    private Long id;
    private String advertisement;

    private Date updatedAt;


    public GeneralAdvertisement() {
    }


    public GeneralAdvertisement(Long id, String advertisement, Date updatedAt) {
        this.id = id;
        this.advertisement = advertisement;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "GeneralAdvertisement{" +
                "id=" + id +
                ", advertisement='" + advertisement + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
