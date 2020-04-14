package com.maths.microservice1.maths.model;

public class Vehicle  {
    private static final long serialVersionUID = 1L;

    public Vehicle() {
    }

    public Vehicle(int id, String type, String modelCode, String brandName, String launchDate) {
        this.id = id;
        this.type = type;
        this.modelCode = modelCode;
        this.brandName = brandName;
        this.launchDate = launchDate;
    }

    private int id;

    private String type;

    private String modelCode;

    private String brandName;

    private String launchDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }
}