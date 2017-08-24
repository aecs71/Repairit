package com.spotify.repairit.models;

import java.util.List;

/**
 * Created by anurag-local on 03-Mar-17.
 */

public class RepairRequest {
    private DeviceDetails deviceDetails;
    private PickupDetails pickupDetails;
    private UserDetails userDetails;

    public RepairRequest(DeviceDetails deviceDetails, PickupDetails pickupDetails, UserDetails userDetails) {
        this.deviceDetails = deviceDetails;
        this.pickupDetails = pickupDetails;
        this.userDetails = userDetails;
    }
    public RepairRequest(){}

    public DeviceDetails getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public PickupDetails getPickupDetails() {
        return pickupDetails;
    }

    public void setPickupDetails(PickupDetails pickupDetails) {
        this.pickupDetails = pickupDetails;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
