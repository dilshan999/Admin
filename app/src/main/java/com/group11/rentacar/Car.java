package com.group11.rentacar;

public class Car {

    private String name;
    private String image;
    private String transmission;
    private String price;

    public Car() {
    }

    public Car(String name, String image, String transmission, String price) {
        this.name = name;
        this.image = image;
        this.transmission = transmission;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
