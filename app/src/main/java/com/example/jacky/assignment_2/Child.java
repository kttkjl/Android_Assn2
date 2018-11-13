package com.example.jacky.assignment_2;

import java.io.Serializable;

public class Child implements Serializable{
    private long _id;
    private String fname;
    private String lname;
    private String bdate;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private float lat;
    private float lng;
    private boolean isNaughty;

    public static final Child[] CHILDREN = {
        new Child("first", "person", "1111-11-11", "WhatverStreet", "city1", "prov1", "101aba", "lulcountry", (float)199.99, (float)89.11, true)
        ,new Child("second", "person", "1111-11-11", "WhatverStreet", "city1", "prov1", "101aba", "lulcountry", (float)199.99, (float)89.11, true)
        ,new Child("third", "person", "1111-11-11", "WhatverStreet", "city1", "prov1", "101aba", "lulcountry", (float)199.99, (float)89.11, true)
        ,new Child("fourth", "person", "1111-11-11", "WhatverStreet", "city1", "prov1", "101aba", "lulcountry", (float)199.99, (float)89.11, false)
        ,new Child("fifth", "person", "1111-11-11", "WhatverStreet", "city1", "prov1", "101aba", "lulcountry", (float)199.99, (float)89.11, false)
    };

    public Child(String fname,
                 String lname,
                 String bdate,
                 String street,
                 String city,
                 String province,
                 String postalCode,
                 String country,
                 float lat,
                 float lng,
                 boolean isNaughty) {
        this.fname = fname;
        this.lname = lname;
        this.bdate = bdate;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
        this.isNaughty = isNaughty;
    }

    public Child(long id,
                 String fname,
                 String lname,
                 String bdate,
                 String street,
                 String city,
                 String province,
                 String postalCode,
                 String country,
                 float lat,
                 float lng,
                 boolean isNaughty) {
        this._id = id;
        this.fname = fname;
        this.lname = lname;
        this.bdate = bdate;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
        this.isNaughty = isNaughty;
    }

    public long getId(){
        return this._id;
    }

    public void setName(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }
    public String getFname(){
        return this.fname;
    }
    public String getLname(){
        return this.lname;
    }
    public void setBdate(String bdate){
        this.bdate = bdate;
    }
    public String getBdate(){
        return this.bdate;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public String getStreet(){
        return this.street;
    }

    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }

    public void setProvince(String province){
        this.province = province;
    }
    public String getProvince(){
        return this.province;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getPostalCode(){
        return this.postalCode;
    }

    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }

    public void setLat(float lat){
        this.lat = lat;
    }
    public float getLat(){
        return this.lat;
    }

    public void setLng(float lng){
        this.lng = lng;
    }
    public float getLng(){
        return this.lng;
    }

    public void setNaughty(boolean naughty){
        this.isNaughty = naughty;
    }
    public boolean isNaughty(){
        return this.isNaughty;
    }
}
