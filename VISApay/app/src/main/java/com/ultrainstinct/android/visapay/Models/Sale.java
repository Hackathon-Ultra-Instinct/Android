package com.ultrainstinct.android.visapay.Models;

public class Sale {
    String product,amount,key;


    public Sale(String product, String amount, String key) {
        this.product = product;
        this.amount = amount;
        this.key = key;
    }

    public Sale() {

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
