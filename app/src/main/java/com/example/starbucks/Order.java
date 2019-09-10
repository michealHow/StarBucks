package com.example.starbucks;

public class Order {

    String userName;
    String productName;
    String productSize;

    public Order(String userName, String productName, String productSize) {
        this.userName = userName;
        this.productName = productName;
        this.productSize = productSize;
    }
     public Order() {

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        productSize = productSize;
    }

    public  String toString()
    {

        return this.userName + ",Ordered :"+productName+", Size :"+productSize;
    }


}
