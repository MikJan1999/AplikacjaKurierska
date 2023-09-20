package com.example.aplikacjakurierska.retrofit.model;

import android.graphics.Bitmap;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Product {

    private Long id;
    private String productName;
    private Double productPrice;
    private byte [] productPictureUrl;
    private String productDescription;
    private Date updatedAt;
    private List<PositionCustomerOrder> positionCustomerOrders;



    public Product() {
    }

    public Product(Long id, String productName, Double productPrice, byte[] productPictureUrl, String productDescription,
                   Date updatedAt, List<PositionCustomerOrder> positionCustomerOrders) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productPictureUrl = productPictureUrl;
        this.productDescription = productDescription;
        this.updatedAt = updatedAt;
        this.positionCustomerOrders = positionCustomerOrders;
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
                ", positionCustomerOrders=" + positionCustomerOrders +
                '}';
    }

    public List<PositionCustomerOrder> getPositionCustomerOrders() {
        return positionCustomerOrders;
    }

    public void setPositionCustomerOrders(List<PositionCustomerOrder> positionCustomerOrders) {
        this.positionCustomerOrders = positionCustomerOrders;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }






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


