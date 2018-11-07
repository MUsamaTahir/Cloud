package com.example.usama.cloud;

public class User {
    public String name, email,city,country,phoneNo;

    public User(){

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.city= null;
        this.country=null;
        this.phoneNo=null;
    }
    public User(String name, String email, String city, String country, String phoneNo) {
        this.name = name;
        this.email = email;
        this.city= city;
        this.country=country;
        this.phoneNo=phoneNo;
    }

}