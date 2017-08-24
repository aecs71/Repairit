package com.spotify.repairit.models;

/**
 * Created by anurag-local on 03-Mar-17.
 */

public class UserDetails {
    private String fullname;
    private String emailAddress;
    private String city;
    private String mobileNo;
    public UserDetails()
    {}

    public UserDetails(String fullname, String emailAddress, String mobileNo, String city) {
        this.fullname = fullname;
        this.emailAddress = emailAddress;
        this.mobileNo = mobileNo;
        this.city = city;
    }



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}



