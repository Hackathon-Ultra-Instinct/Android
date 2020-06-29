package com.ultrainstinct.android.visapay.Models;

public class ParkingDetails {
    String carNumber,key,parkingTime;

    public ParkingDetails(String carNumber, String key, String parkingTime) {
        this.carNumber = carNumber;
        this.key = key;
        this.parkingTime = parkingTime;
    }

    public ParkingDetails() {

    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(String parkingTime) {
        this.parkingTime = parkingTime;
    }
}
