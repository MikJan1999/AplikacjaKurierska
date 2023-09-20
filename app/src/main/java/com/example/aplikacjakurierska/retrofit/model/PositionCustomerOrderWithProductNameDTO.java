package com.example.aplikacjakurierska.retrofit.model;

public class PositionCustomerOrderWithProductNameDTO {
    private Long positionId;
    private float amount;
    private String pcoDesc;
    private float priceAll;
    private String productName;

    public PositionCustomerOrderWithProductNameDTO(Long positionId,
                                                   float amount, String pcoDesc, float priceAll, String productName) {
        this.positionId = positionId;
        this.amount = amount;
        this.pcoDesc = pcoDesc;
        this.priceAll = priceAll;
        this.productName = productName;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPcoDesc() {
        return pcoDesc;
    }

    public void setPcoDesc(String pcoDesc) {
        this.pcoDesc = pcoDesc;
    }

    public float getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(float priceAll) {
        this.priceAll = priceAll;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Override
    public String toString() {
        return "PositionCustomerOrderWithProductNameDTO{" +
                "positionId=" + positionId +
                ", amount=" + amount +
                ", pcoDesc='" + pcoDesc + '\'' +
                ", priceAll=" + priceAll +
                ", productName='" + productName + '\'' +
                '}';
    }
}
