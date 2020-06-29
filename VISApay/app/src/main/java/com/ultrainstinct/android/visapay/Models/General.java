package com.ultrainstinct.android.visapay.Models;

public class General {

    String name,phone,cardNumber,cityName,pincode,carNumber,userId,key;

    public General(String name, String phone, String cardNumber, String cityName, String pincode, String carNumber, String userId, String key) {
        this.name = name;
        this.phone = phone;
        this.cardNumber = cardNumber;
        this.cityName = cityName;
        this.pincode = pincode;
        this.carNumber = carNumber;
        this.userId = userId;
        this.key = key;
    }

    public General() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
