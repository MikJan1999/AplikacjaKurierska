package com.example.aplikacjakurierska.retrofit.model;

import android.graphics.Bitmap;

import java.util.Arrays;
import java.util.Date;

public class Product {

    private Long id;
    private String productName;
    private Double productPrice;
    private byte [] productPictureUrl;
    private String productDescription;
    private Date updatedAt;
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    //Czy produkt jest na sprzedaż czy został wycofany
//    private Date createdAt;


    public Product(Long id, String productName, Double productPrice,String productDescription,
//                   Date createdAt
            Date updatedAt)
    {

        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
this.updatedAt = updatedAt;
//        this.createdAt = createdAt;

    }



    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productPictureUrl=" + Arrays.toString(productPictureUrl) +
                ", productDescription='" + productDescription + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public byte[] getProductPictureUrl() {
        return productPictureUrl;
    }

    public void setProductPictureUrl(byte []  productPictureUrl) {
        this.productPictureUrl = productPictureUrl;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }




}


