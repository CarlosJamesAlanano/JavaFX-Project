package com.example.finalfx.last;

public class Booking {
    private String bookingType;
    private Drivers driver;
    private String location;

    public Booking(String bookingType, Drivers driver, String location) {
        this.bookingType = bookingType;
        this.driver = driver;
        this.location = location;
    }

    public String getBookingType() {
        return bookingType;
    }

    public Drivers getDriver() {
        return driver;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Type: " + bookingType + ", Driver: " + driver.getName() + ", Location: " + location;
    }
}
