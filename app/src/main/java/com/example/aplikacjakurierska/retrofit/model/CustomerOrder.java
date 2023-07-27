package com.example.aplikacjakurierska.retrofit.model;

import java.util.List;

public class CustomerOrder {

    private Long id;
    //czy zamówienie jest w koszyku
    private Boolean isCartShop;
    //czy został sprzedany
    private Boolean isSaled;
    private List<PositionCustomerOrder> positionCustomerOrders;
    private Address address;

    public CustomerOrder(Long id, Boolean isCartShop, Boolean isSaled, List<PositionCustomerOrder> positionCustomerOrders, Address address) {
        this.id = id;
        this.isCartShop = isCartShop;
        this.isSaled = isSaled;
        this.positionCustomerOrders = positionCustomerOrders;
        this.address = address;
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

    public Boolean getSaled() {
        return isSaled;
    }

    public void setSaled(Boolean saled) {
        isSaled = saled;
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
                ", isSaled=" + isSaled +
                ", positionCustomerOrders=" + positionCustomerOrders +
                ", address=" + address +
                '}';
    }
}
