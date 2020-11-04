package com.zuehlke.securesoftwaredevelopment.domain;

public class Car {
    private Integer id;
    private double price;
    private double wholesalePrice;
    private String model;
    private String manufacturer;
    private Integer manufactureYear;

    public Car(Integer id, double price, double wholesalePrice, String model, String manufacturer, Integer manufactureYear) {
        this.id = id;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.model = model;
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }
}
