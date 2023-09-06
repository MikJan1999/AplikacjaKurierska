package com.example.aplikacjakurierska.retrofit.model;

import java.util.List;

public class CustomerOrder {

    private Long id;
    //czy zamówienie jest w koszyku
    private Boolean isCartShop;
    private List<PositionCustomerOrder> positionCustomerOrders;
    private Address address;

    public CustomerOrder(Long id, Boolean isCartShop, Boolean isSaled, List<PositionCustomerOrder> positionCustomerOrders, Address address) {
        this.id = id;
        this.isCartShop = isCartShop;
        this.positionCustomerOrders = positionCustomerOrders;
        this.address = address;
    }

    public CustomerOrder(List<PositionCustomerOrder> positionCustomerOrders) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCartShop() {
        return isCartShop;
    }

    public void setCartShop(Boolean cartShop) {
        isCartShop = cartShop;
    }



    public List<PositionCustomerOrder> getPositionCustomerOrders() {
        return positionCustomerOrders;
    }

    public void setPositionCustomerOrders(List<PositionCustomerOrder> positionCustomerOrders) {
        this.positionCustomerOrders = positionCustomerOrders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", isCartShop=" + isCartShop +
                ", positionCustomerOrders=" + positionCustomerOrders +
                ", address=" + address +
                '}';
    }
}
