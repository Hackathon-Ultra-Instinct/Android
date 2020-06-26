package com.ultrainstinct.android.visapay.Models;

public class Cart {
    String userId,product,key;

    public Cart(String userId, String product, String key) {
        this.userId = userId;
        this.product = product;
        this.key = key;
    }

    public Cart() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}