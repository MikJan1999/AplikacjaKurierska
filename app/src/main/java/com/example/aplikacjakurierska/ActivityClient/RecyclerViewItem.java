package com.example.aplikacjakurierska.ActivityClient;

public class RecyclerViewItem {
    private boolean isHeader;
    private String headerText;
    private String productName;
    private String productPrice;
    private String productAmount;

    public RecyclerViewItem(boolean isHeader, String headerText) {
        this.isHeader = isHeader;
        this.headerText = headerText;
    }

    public RecyclerViewItem(String productName, String productPrice, String productAmount) {
        this.isHeader = false;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }
}
