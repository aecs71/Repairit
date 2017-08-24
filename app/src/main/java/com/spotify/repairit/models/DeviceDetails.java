package com.spotify.repairit.models;

/**
 * Created by anurag-local on 03-Mar-17.
 */

public class DeviceDetails  {

    private String deviceType;
    private String deviceBrand;
    private String deviceModel;
    private String deviceIssue;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public DeviceDetails(){}
    public DeviceDetails(String deviceType, String deviceBrand, String deviceModel, String deviceIssue) {
        this.deviceBrand = deviceBrand;
        this.deviceType = deviceType;
        this.deviceModel = deviceModel;
        this.deviceIssue = deviceIssue;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;

    }

    public String getDeviceIssue() {
        return deviceIssue;
    }

    public void setDeviceIssue(String deviceIssue) {
        this.deviceIssue = deviceIssue;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
