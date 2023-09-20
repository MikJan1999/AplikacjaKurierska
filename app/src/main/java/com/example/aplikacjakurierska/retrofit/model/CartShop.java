package com.example.aplikacjakurierska.retrofit.model;

import java.util.List;

public class CartShop {
    private Long id;
    private List<PositionCustomerOrder> positionCustomerOrderList;
    private User user;


    public CartShop(Long id, List<PositionCustomerOrder> positionCustomerOrderList, User user) {
        this.id = id;
        this.positionCustomerOrderList = positionCustomerOrderList;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PositionCustomerOrder> getPositionCustomerOrderList() {
        return positionCustomerOrderList;
    }

    public void setPositionCustomerOrderList(List<PositionCustomerOrder> positionCustomerOrderList) {
        this.positionCustomerOrderList = positionCustomerOrderList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CartShop{" +
                "id=" + id +
                ", positionCustomerOrderList=" + positionCustomerOrderList +
                ", user=" + user +
                '}';
    }
}
