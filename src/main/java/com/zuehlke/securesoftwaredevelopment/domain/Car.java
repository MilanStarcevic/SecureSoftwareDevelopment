package com.zuehlke.securesoftwaredevelopment.domain;

public class Car {
    private Integer id;
    private Double price;
    private Double wholesalePrice;
    private String model;
    private String manufacturer;

    public Car(Integer id, Double price, Double wholesalePrice, String model, String manufacturer) {
        this.id = id;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(Double wholesalePrice) {
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
}
