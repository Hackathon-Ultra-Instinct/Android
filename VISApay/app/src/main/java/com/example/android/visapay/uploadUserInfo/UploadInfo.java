package com.example.android.visapay.uploadUserInfo;

public class UploadInfo {
    String name;
    long phone;
    long cardNumber;
    int pinCode;
    String cityName;

    public UploadInfo(String name, long phone, long cardNumber, int pinCode, String cityName) {
        this.name = name;
        this.phone = phone;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.cityName = cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
