package com.example.ooplab2;


import java.io.Serializable;

public class Car implements Serializable
{
    private String name, model, bodyType, transmission, engineType, mail, number;

    private int realeseDate, mileage;

//
    public Car(String name, String model, String bodyType, String transmission, String engineType, int realeseDate, int mileage,
              String mail, String number) {
        this.name = name;
        this.model = model;
        this.bodyType = bodyType;
        this.transmission = transmission;
        this.engineType = engineType;
        this.realeseDate = realeseDate;
        this.mileage = mileage;
        this.mail = mail;
        this.number = number;

    }
    public Car() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getRealeseDate() {
        return realeseDate;
    }

    public void setRealeseDate(int realeseDate) {
        this.realeseDate = realeseDate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public  String toString(){
        return "Name: " + name + " Model: " + model + "BodyType";
    }


}
