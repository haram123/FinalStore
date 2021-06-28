package com.example.ecommerceproject;

public class myCartModel {
    String currenttime;
    String currentdate;
    String productname;
    String productprice;
    String totalquantity;
    int totalrpice;

    public myCartModel() {
    }

    public myCartModel(String currenttime, String currentdate, String productname, String productprice, String totalquantity, int totalrpice) {
        this.currenttime = currenttime;
        this.currentdate = currentdate;
        this.productname = productname;
        this.productprice = productprice;
        this.totalquantity = totalquantity;
        this.totalrpice = totalrpice;
    }

    public String getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getTotalquantity() {
        return totalquantity;
    }

    public void setTotalquantity(String totalquantity) {
        this.totalquantity = totalquantity;
    }

    public int getTotalrpice() {
        return totalrpice;
    }

    public void setTotalrpice(int totalrpice) {
        this.totalrpice = totalrpice;
    }
}
