package com.example.aplikacjakurierska.retrofit.model;

public class PositionCustomerOrder {


    private Long id;
    private float amount;
    //czy akceptowano dane zamówienie i czy zostanie zrealizowane

    private CustomerOrder customerOrder;
    private Product product;

    private float priceAll;
    private CartShop cartShop;

    public PositionCustomerOrder(Long id, float amount, CustomerOrder customerOrder, Product product,
                                 float priceAll, CartShop cartShop) {
        this.id = id;
        this.amount = amount;
        this.customerOrder = customerOrder;
        this.product = product;
        this.priceAll = priceAll;
        this.cartShop = cartShop;

    }



    @Override
    public String toString() {
        return "PositionCustomerOrder{" +
                "id=" + id +
                ", amount=" + amount +
                ", customerOrder=" + customerOrder +
                ", product=" + product +
                ", priceAll=" + priceAll +
                ", cartShop=" + cartShop +
                '}';
    }




    public CartShop getCartShop() {
        return cartShop;
    }

    public void setCartShop(CartShop cartShop) {
        this.cartShop = cartShop;
    }

    public float getPriceAll() {
        return priceAll;
    }
    public void setPriceAll(float priceAll) {
        this.priceAll = priceAll;
    }
    public PositionCustomerOrder() {}
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
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }
    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;}
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}

