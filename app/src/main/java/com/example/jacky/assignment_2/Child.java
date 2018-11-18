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
    private String dateCreated;

    // Seed Data of 5 Records
    public static final Child[] CHILDREN = {
        new Child("John", "Doe", "2007-10-23", "Sesame Street", "Burnaby", "British Columbia", "V5K 1B4", "Canada", (float)45.23, (float)-123.11, true)
        ,new Child("Jane", "Doe", "2008-02-22", "Corner Street", "Prince George", "British Columbia", "V2L 8U9", "Canada", (float)22.13, (float)-102.31, true)
        ,new Child("Billy", "Bob", "2010-11-23", "West Street", "Surrey", "British Columbia", "V5T 9L1", "Canada", (float)-23.68, (float)-38.91, true)
        ,new Child("Rachel", "Bob", "2008-01-17", "West Street", "Surrey", "British Columbia", "V5T 9L1", "Canada", (float)199.99, (float)89.11, false)
        ,new Child("Michael", "Point", "2004-05-01", "Winter Street", "Langley", "British Columbia", "V7E 3F5", "Canada", (float)-89.73, (float)101.12, false)
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

    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public String getDateCreated() { return this.dateCreated; }
}
