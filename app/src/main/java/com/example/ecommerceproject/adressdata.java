package com.example.ecommerceproject;
public class adressdata {
    String address;
    Boolean clickaddress;

    public adressdata() {
    }

    public adressdata(String address,Boolean clickaddress) {
        this.address = address;
        this.clickaddress=clickaddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getClickaddress() {
        return clickaddress;
    }

    public void setClickaddress(Boolean clickaddress) {
        this.clickaddress = clickaddress;
    }
}