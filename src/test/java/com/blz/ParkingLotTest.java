package com.blz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot();
    }

    /**
     * UC1
     *TC1 = Ability To Park vehicle
     *Given vehicle when parked should return true
     */
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLot.vehicleParking(vehicle);
            boolean isParked = parkingLot.isParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }
    /**
     * UC1
     * TC2 = Ability To Park vehicle
     * given a vehicle when already parked should throw exception
     */
    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldThrowException() {
        Vehicle vehicle = new Vehicle();

        try {
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleParking(new Vehicle());
            boolean isParked = parkingLot.isParked(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is full", e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * UC2
     * TC3 = Ability To unPark vehicle
     * Given a vehicle when unparked should return true
     */
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLot.vehicleParking(vehicle);
            boolean isUnParked = parkingLot.isUnParked(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }
    /**
     * UC2
     * TC4 = Ability To unPark vehicle
     * given a unparked vehicle when try to unpark should return throw exception
     */
    @Test
    public void givenUnParkedVehicle_WhenTryToUnPark_ShouldReturnThrowException() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLot.vehicleUnparking(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Lot is empty", e.getMessage());
            e.printStackTrace();
        }

    }
    /**
     * UC2
     * TC5 = Ability To unPark vehicle
     * Given a vehicle when try to unpark different vehicle should throw exception
     */
    @Test
    public void givenVehicle_WhenTryToUnParkDifferentVehicle_ShouldThrowException() {
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle1 = new Vehicle();
        try {
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleUnparking(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Please ask correct vehicle", e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * UC3
     * TC5 = Ability To check parking lot is full by owner
     * Given a vehicle when parking lot is full should give message to owner
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToOwner() {
        Vehicle vehicle = new Vehicle();
        try {
            parkingLot.vehicleParking(vehicle);
            Owner owner = new Owner();
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot is full",status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
