package com.example.dbpractice;

public class Bus {
    private int busID;
    private String busRoute;
    private double busLatitude;
    private double busLongitude;

    public Bus(int busID, String busRoute, double busLatitude, double busLongitude) {
        this.busID = busID;
        this.busRoute = busRoute;
        this.busLatitude = busLatitude;
        this.busLongitude = busLongitude;
    }

    public Bus() {
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public String getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(String busRoute) {
        this.busRoute = busRoute;
    }

    public double getBusLatitude() {
        return busLatitude;
    }

    public void setBusLatitude(double busLatitude) {
        this.busLatitude = busLatitude;
    }

    public double getBusLongitude() {
        return busLongitude;
    }

    public void setBusLongitude(double busLongitude) {
        this.busLongitude = busLongitude;
    }
}
