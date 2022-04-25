package com.blz;

public class ParkingLot {
    Vehicle vehicle;

    public boolean park(Vehicle vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    public boolean unPark(Vehicle vehicle) {
        if (this.vehicle == null)
            return false;
        if (this.vehicle.equals(vehicle)) {
            this.vehicle=null;
            return true;
        }
        return false;
    }
}

