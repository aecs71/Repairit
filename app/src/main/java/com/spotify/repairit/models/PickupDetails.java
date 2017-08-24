package com.spotify.repairit.models;

/**
 * Created by anurag-local on 03-Mar-17.
 */

public class PickupDetails {
    private String pickupDate;
public PickupDetails(){}
    public PickupDetails(String pickupDate, String pickupAddress, String pickupLandmark, String pincode) {
        this.pickupDate = pickupDate;
        this.pickupAddress = pickupAddress;
        this.pickupLandmark = pickupLandmark;
        this.pincode = pincode;
    }

    private String pickupAddress;

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupLandmark() {
        return pickupLandmark;
    }

    public void setPickupLandmark(String pickupLandmark) {
        this.pickupLandmark = pickupLandmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    private String pickupLandmark;
    private String pincode;
}
