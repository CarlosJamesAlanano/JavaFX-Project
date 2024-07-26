package com.example.finalfx.last;

public class Drivers {
    private String name;
    private String vehicle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Drivers(String name, String vehicle) {
        this.name = name;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return name + " - " + vehicle;
    }
}
