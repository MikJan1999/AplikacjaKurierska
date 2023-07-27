package com.example.aplikacjakurierska.retrofit.model;

public class PositionCustomerOrder {


    private Long id;
    private float amount;
    //czy akceptowano dane zamówienie i czy zostanie zrealizowane
    private Boolean isAccepted;
    private Boolean isDelivered;
    private CustomerOrder customerOrder;
    private Product product;

    public PositionCustomerOrder(Long id, float amount, Boolean isAccepted, Boolean isDelivered,
                                 CustomerOrder customerOrder, Product product) {
        this.id = id;
        this.amount = amount;
        this.isAccepted = isAccepted;
        this.isDelivered = isDelivered;
        this.customerOrder = customerOrder;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public String toString() {
        return "PositionCustomerOrder{" +
                "id=" + id +
                ", amount=" + amount +
                ", isAccepted=" + isAccepted +
                ", isDelivered=" + isDelivered +
                ", customerOrder=" + customerOrder +
                ", product=" + product +
                '}';
    }
}

