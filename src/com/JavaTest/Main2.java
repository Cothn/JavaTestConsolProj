package com.JavaTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Car {

    public static int carCounter = 10;

    protected String description = "Абстрактная машина";
    static {
        System.out.println("Static Car initializer"+carCounter+"!");
    }

    {
        System.out.println("Dynamic Car initializer"+carCounter+"!"+description+"!!"+Truck.truckCounter+"!");
    }

    public Car() {
        System.out.println("Car Constructor"+carCounter+"!"+description+"!!"+Truck.truckCounter+"!");
    }

    public String getDescription() {
        return description;
    }
}

class Truck extends Car {

    public static int truckCounter = 2;

    private int yearOfManufacture ;
    private String model = "default";
    private int maxSpeed;
    static {
        System.out.println("Static Truck initializer"+carCounter+"!"+truckCounter+"!");
    }
    {
        System.out.println("Dynamic Truck initializer"+carCounter+"!"+truckCounter+"!"+description+"!!"+model+"!!");
    }


    public Truck(int yearOfManufacture, String model, int maxSpeed) {
        System.out.println("Truck  Constructor "+carCounter+"!"+truckCounter+"!"+description+"!!"+this.model+"!!");

        this.yearOfManufacture = yearOfManufacture;
        this.model = model;
        this.maxSpeed = maxSpeed;

        Car.carCounter++;
        truckCounter++;
    }
}

public class Main2 {
    public static void main(String[] args) throws IOException {

        Truck truck = new Truck(2017, "Scania S 500 4x2", 220);
    }
}
