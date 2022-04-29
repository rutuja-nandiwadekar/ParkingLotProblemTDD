package com.blz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ParkingLotTest {
    ParkingLot parkingLot = null;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot();
    }

    /**
     * UC1
     * TC1 = Ability To Park vehicle
     * Given vehicle when parked should return true
     */
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("alto", 1);
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
        Vehicle vehicle = new Vehicle("alto", 1);
        Vehicle vehicle1 = new Vehicle("alto 80", 2);
        Vehicle vehicle2 = new Vehicle("brezza", 3);
        try {
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
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
        Vehicle vehicle = new Vehicle("alto", 1);
        try {
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleUnparking(vehicle);
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
        Vehicle vehicle = new Vehicle("alto", 1);
        try {
            parkingLot.vehicleUnparking(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("lot is empty", e.getMessage());
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
        Vehicle vehicle = new Vehicle("alto", 1);
        Vehicle vehicle1 = new Vehicle("alto 80", 2);
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
     * TC6 = Ability To check parking lot is full by owner
     * Given a vehicle when parking lot is full should give message to owner
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToOwner() {
        Vehicle vehicle1 = new Vehicle("alto", 1);
        Vehicle vehicle2 = new Vehicle("brezza", 2);
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot is full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC4
     * TC7 = Ability to check the security personal is getting correct message when lot is full
     * Given a vehicle when parking lot is full should give message to security personal
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToSecurityPersonal() {
        Vehicle vehicle1 = new Vehicle("alto", 1);
        Vehicle vehicle2 = new Vehicle("brezza", 2);
        try {
            SecurityPersonal securityPersonal = new SecurityPersonal();
            parkingLot.registerObserver(securityPersonal);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            String status = securityPersonal.getStatus();
            Assert.assertEquals("Parking lot is full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC5
     * TC8 = Ability to check the owner is getting correct message when Parking lot has space
     * Given a vehicle when parking lot has space again should give message to owner
     */
    @Test
    public void givenAVehicle_WhenParkingLotHasSpaceAgain_ShouldGiveMessageToOwner() {
        Vehicle vehicle = new Vehicle("alto", 1);
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleUnparking(vehicle);
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot has space", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC6
     * TC9 =
     * given attendant when owner gives the slot to park the vehicle should park
     */
    @Test
    public void givenAttendant_WhenOwnerGivesTheSlotToParkTheVehicle_ShouldPark() {
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            Vehicle vehicle1 = new Vehicle("alto", 1);
            Vehicle vehicle2 = new Vehicle("brezza", 2);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            int vehicleLotNumber = parkingLot.getVehicleLotNumber(vehicle2);
            Assert.assertEquals(2, vehicleLotNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC7
     * TC9 =
     * given vehicle when find vehicle should return key
     */
    @Test
    public void givenVehicle_WhenVehicleFind_ShouldReturnKey() {
        try {
            Vehicle vehicle1 = new Vehicle("alto", 1);
            Vehicle vehicle2 = new Vehicle("audi", 2);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            int key = parkingLot.getVehicleLocation(vehicle2);
            Assert.assertEquals(2, key);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC8
     * TC9 =
     * given vehicle when find vehicle should return key
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTimeOfParking() {
        try {
            Vehicle vehicle1 = new Vehicle("alto", 1);
            parkingLot.vehicleParking(vehicle1);
            LocalDateTime localDateTime = LocalDateTime.now();
            Assert.assertEquals(localDateTime,parkingLot.getParkedTime());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
