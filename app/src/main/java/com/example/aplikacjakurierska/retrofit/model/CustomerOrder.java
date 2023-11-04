package com.example.aplikacjakurierska.retrofit.model;

import android.provider.ContactsContract;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CustomerOrder {

    private Long id;
    private StatusOrder statusOrder;

    private String description;
    private List<PositionCustomerOrder> positionCustomerOrdersss;
    private Date dataCreateOrder;

    private Float priceOrder;


    //address
    private String nameAndSurname;
    private String street;
    private String numberOfHouse;
    private String village;
    private int numberOfPhone;



//    private Address address;

    private User user;

    public CustomerOrder() {
    }

    public CustomerOrder(Long id, StatusOrder statusOrder, String description, List<PositionCustomerOrder> positionCustomerOrdersss, Date dataCreateOrder, Float priceOrder, String nameAndSurname, String street, String numberOfHouse, String village, int numberOfPhone, User user) {
        this.id = id;
        this.statusOrder = statusOrder;
        this.description = description;
        this.positionCustomerOrdersss = positionCustomerOrdersss;
        this.dataCreateOrder = dataCreateOrder;
        this.priceOrder = priceOrder;
        this.nameAndSurname = nameAndSurname;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
        this.village = village;
        this.numberOfPhone = numberOfPhone;
        this.user = user;
    }

    public Float getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(Float priceOrder) {
        this.priceOrder = priceOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PositionCustomerOrder> getPositionCustomerOrdersss() {
        return positionCustomerOrdersss;
    }

    public void setPositionCustomerOrdersss(List<PositionCustomerOrder> positionCustomerOrdersss) {
        this.positionCustomerOrdersss = positionCustomerOrdersss;
    }

    public Date getDataCreateOrder() {
        return dataCreateOrder;
    }

    public void setDataCreateOrder(Date dataCreateOrder) {
        this.dataCreateOrder = dataCreateOrder;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(String numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public int getNumberOfPhone() {
        return numberOfPhone;
    }

    public void setNumberOfPhone(int numberOfPhone) {
        this.numberOfPhone = numberOfPhone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", statusOrder=" + statusOrder +
                ", description='" + description + '\'' +
                ", positionCustomerOrdersss=" + positionCustomerOrdersss +
                ", dataCreateOrder=" + dataCreateOrder +
                ", priceOrder=" + priceOrder +
                ", nameAndSurname='" + nameAndSurname + '\'' +
                ", street='" + street + '\'' +
                ", numberOfHouse=" + numberOfHouse +
                ", village='" + village + '\'' +
                ", numberOfPhone=" + numberOfPhone +
                ", user=" + user +
                '}';
    }
}
