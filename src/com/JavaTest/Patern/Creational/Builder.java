package com.JavaTest.Patern.Creational;


import org.jetbrains.annotations.NotNull;


class Car  {
    public int seats;
    public String engine;
    public boolean tripComputer = false;
    public boolean gps = false;

    public String toString (){
        return "Seats number: " + seats + " Engine: " + engine + " Computer: " + tripComputer + " GPS: " + gps;
    }
}

class Manual {
    public int seats;
    public String engine;
    public String tripComputer;
    public String gps;

    public String toString (){
        return "Seats number: " + seats + " Engine: " + engine + " Computer desc: " + tripComputer + " GPS desc: " + gps;
    }
}


interface AbstractBuilder{

    boolean reset();
    boolean setSeats(int number);
    boolean setEngine(String engine);
    boolean setTripComputer(String tripComputer);
    boolean setGps(String gps);
}

class CarBuilder implements AbstractBuilder{
    private Car car;
    public boolean reset(){
        try {
            car = new Car();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setSeats(int number){
        try {
            car.seats = number;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setEngine(String engine){
        try {
            if ( engine != null) {
                car.engine = engine;
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setTripComputer(String tripComputer){
        try {
            car.tripComputer = true;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setGps(String gps){
        try {
            car.gps = true;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public Car getResult(){
        return car;
    }
}
class ManualBuilder implements AbstractBuilder{
    private Manual manual;
    public boolean reset(){
        try {
            manual = new Manual();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setSeats(int number){
        try {
            manual.seats = number;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setEngine(String engine){
        try {
            if ( engine != null) {
                manual.engine = engine;
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setTripComputer(String tripComputer){
        try {
            manual.tripComputer = tripComputer;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean setGps(String gps){
        try {
            manual.gps = gps;
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public Manual getResult(){
        return manual;
    }
}

class Director{

    public void makeSUV(@NotNull AbstractBuilder builder){
        builder.reset();
        builder.setSeats(4);
        builder.setEngine("SUV engine");
        builder.setGps("GPS Description");
        builder.setTripComputer("SUV TripComputer description");
    }
    public void makeSportCar(@NotNull AbstractBuilder builder){
        builder.reset();
        builder.setSeats(2);
        builder.setEngine("Sport engine");
        builder.setTripComputer("SportCar TripComputer description");
    }
}



public class Builder {


    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder carBuilder = new CarBuilder();
        ManualBuilder manualBuilder = new ManualBuilder();

        director.makeSUV(carBuilder);
        Car car = carBuilder.getResult();
        System.out.println("Car: "+car+"\n");
        director.makeSUV(manualBuilder);
        Manual manual = manualBuilder.getResult();
        System.out.println("Manual: "+manual+"\n\n");

        director.makeSportCar(carBuilder);
        car = carBuilder.getResult();
        System.out.println("Car: "+car+"\n");
        director.makeSportCar(manualBuilder);
        manual = manualBuilder.getResult();
        System.out.println("Manual: "+manual+"\n\n");
    }


}
