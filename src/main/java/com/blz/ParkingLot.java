package com.blz;

public class ParkingLot {
    private Vehicle vehicle;

    public boolean vehicleParking(Vehicle vehicle) {
        if (this.vehicle != null) {
            return false;
        } else {
            this.vehicle = vehicle;
            return true;
        }
    }
}
