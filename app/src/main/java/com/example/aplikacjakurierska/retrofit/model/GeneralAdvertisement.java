package com.example.aplikacjakurierska.retrofit.model;

import java.util.Date;

public class GeneralAdvertisement {
    public GeneralAdvertisement() {
    }

    private Long id;
    private String advertisement;
    private Date dataOfAdding;
//
    public GeneralAdvertisement(Long id, String advertisement
                                ,Date dataOfAdding) {

        this.id = id;
        this.advertisement = advertisement;
        this.dataOfAdding = dataOfAdding;
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

    public Date getDataOfAdding() {
        return dataOfAdding;
    }

    public void setDataOfAdding(Date dataOfAdding) {
        this.dataOfAdding = dataOfAdding;
    }


    @Override
    public String toString() {
        return "GeneralAdvertisement{" +
                "id=" + id +
                ", advertisement='" + advertisement + '\'' +
                ", dataOfAdding=" + dataOfAdding +
                '}';
    }
}
