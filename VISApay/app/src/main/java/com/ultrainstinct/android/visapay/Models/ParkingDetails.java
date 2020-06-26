package com.ultrainstinct.android.visapay.Models;

import java.util.Date;

public class ParkingDetails {
    String cardNumber,key;
    Date parkingTime;

    public ParkingDetails(String cardNumber, String key, Date parkingTime) {
        this.cardNumber = cardNumber;
        this.key = key;
        this.parkingTime = parkingTime;
    }

    public ParkingDetails() {

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(Date parkingTime) {
        this.parkingTime = parkingTime;
    }
}
