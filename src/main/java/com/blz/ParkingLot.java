package com.blz;

public class ParkingLot {
    private Vehicle vehicle;
    public static Owner owner = new Owner();

    /**
     * @Purpose : To park the vehicle
     * @Param : vehicle
     */

    public void vehicleParking(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking lot is full");
        this.vehicle = vehicle;
        if(this.vehicle != null){
            String message = "Parking lot is full";
            owner.update(message);
        }
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

