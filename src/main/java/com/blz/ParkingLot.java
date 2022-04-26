package com.blz;

public class ParkingLot {
    private Vehicle vehicle;

    //default constructor
    public ParkingLot() {
    }

    /**
     * @Purpose : To park the vehicle
     * @Param : vehicle
     */

    public void vehicleParking(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking Lot is Full");
        this.vehicle = vehicle;
    }
    /**
     * @Purpose : To unPark the vehicle
     * @Param : vehicle
     */
    public void vehicleUnparking(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicle == null)
            throw new ParkingLotException("Lot is empty");
        if (this.vehicle .equals(vehicle)){
            this.vehicle = null;
            return;
        }
        throw new ParkingLotException("Please ask correct vehicle");
    }
    /**
     * @Purpose : Method to check vehicle is park or not
     * @Param : vehicle
     * @Return : Returns boolean value true or false
     */
    public boolean isParked(Vehicle vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
    /**
     * @Purpose :  Method to check vehicle is unpark or not
     * @Param : vehicle
     * @Return : Returns boolean value true or false
     */
    public boolean isUnParked(Vehicle vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}

