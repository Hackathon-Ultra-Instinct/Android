package com.ultrainstinct.android.visapay.Models;

public class Sale {
    String product,department,amount,key;


    public Sale(String product, String department, String amount, String key) {
        this.product = product;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
