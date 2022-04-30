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
     * @UC1 TC1 = Ability To Park vehicle.
     * Given vehicle when parked should return true.
     */
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("alto", 1);
        try {
            parkingLot.vehicleParking(vehicle, DriverType.NORMAL, CarType.SMALL);
            boolean isParked = parkingLot.isParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    /**
     * @UC1 TC2 = Ability To Park vehicle.
     * given a vehicle when already parked should throw exception.
     */
    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldThrowException() {
        Vehicle vehicle = new Vehicle("alto", 1);
        Vehicle vehicle1 = new Vehicle("alto 80", 2);
        Vehicle vehicle2 = new Vehicle("brezza", 3);
        try {
            parkingLot.vehicleParking(vehicle, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(vehicle2, DriverType.NORMAL, CarType.SMALL);
            boolean isParked = parkingLot.isParked(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is full", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @UC2 TC3 = Ability To unPark vehicle.
     * Given a vehicle when unparked should return true.
     */
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("alto", 1);
        try {
            parkingLot.vehicleParking(vehicle, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleUnparking(vehicle);
            boolean isUnParked = parkingLot.isUnParked(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    /**
     * @UC2 TC4 = Ability To unPark vehicle.
     * given a unparked vehicle when try to unpark should return throw exception.
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
     * @UC2 TC5 = Ability To unPark vehicle.
     * Given a vehicle when try to unpark different vehicle should throw exception.
     */
    @Test
    public void givenVehicle_WhenTryToUnParkDifferentVehicle_ShouldThrowException() {
        Vehicle vehicle = new Vehicle("alto", 1);
        Vehicle vehicle1 = new Vehicle("alto 80", 2);
        try {
            parkingLot.vehicleParking(vehicle, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleUnparking(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Please ask correct vehicle", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @UC3 TC6 = Ability To check parking lot is full by owner
     * Given a vehicle when parking lot is full should give message to owner.
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToOwner() {
        Vehicle vehicle1 = new Vehicle("alto", 1);
        Vehicle vehicle2 = new Vehicle("brezza", 2);
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(vehicle2, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("ertiga", 3), DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("altroz", 4), DriverType.NORMAL, CarType.SMALL);
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot is full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC4 TC7 = Ability to check the security personal is getting correct message when lot is full
     * Given a vehicle when parking lot is full should give message to security personal.
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToSecurityPersonal() {
        Vehicle vehicle1 = new Vehicle("alto", 1);
        Vehicle vehicle2 = new Vehicle("brezza", 2);
        try {
            SecurityPersonal securityPersonal = new SecurityPersonal();
            parkingLot.registerObserver(securityPersonal);
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(vehicle2, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("ertiga", 3), DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("altroz", 4), DriverType.NORMAL, CarType.SMALL);
            String status = securityPersonal.getStatus();
            Assert.assertEquals("Parking lot is full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC5 TC8 = Ability to check the owner is getting correct message when Parking lot has space
     * Given a vehicle when parking lot has space again should give message to owner.
     */
    @Test
    public void givenAVehicle_WhenParkingLotHasSpaceAgain_ShouldGiveMessageToOwner() {
        Vehicle vehicle = new Vehicle("alto", 1);
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            parkingLot.vehicleParking(vehicle, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleUnparking(vehicle);
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot has space", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC6 TC9 = Introducing attendant for parking lot to park cars in parking slots by owners choice.
     */
    @Test
    public void givenAttendant_WhenOwnerGivesTheSlotToParkTheVehicle_ShouldPark() {
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            Vehicle vehicle1 = new Vehicle("alto", 1);
            Vehicle vehicle2 = new Vehicle("brezza", 2);
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(vehicle2, DriverType.NORMAL, CarType.SMALL);
            int vehicleLotNumber = parkingLot.getVehicleLotNumber(vehicle2);
            Assert.assertEquals(2, vehicleLotNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC7 TC10 = Driver Wants to Find parked car.
     */
    @Test
    public void givenVehicle_WhenVehicleFind_ShouldReturnKey() {
        try {
            Vehicle vehicle1 = new Vehicle("alto", 1);
            Vehicle vehicle2 = new Vehicle("audi", 2);
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(vehicle2, DriverType.NORMAL, CarType.SMALL);
            int key = parkingLot.getVehicleLocation(vehicle2);
            Assert.assertEquals(2, key);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC8 TC11 = Parking Lot owner Wants to know time in and time out to charge parking lot users.
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTimeOfParking() {
        try {
            Vehicle vehicle1 = new Vehicle("alto", 1);
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            LocalDateTime localDateTime = LocalDateTime.now();
            Assert.assertEquals(localDateTime, parkingLot.getParkedTime());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC9 TC12 = Parking lot owner wants parking attendant to direct cars in evenly manner
     * to ensure Evenly Distributed Parking .
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldParkEvenly() {
        Vehicle vehicle1 = new Vehicle("alto", 1);
        Vehicle vehicle2 = new Vehicle("brezza", 2);
        Vehicle vehicle3 = new Vehicle("etios", 3);
        Vehicle vehicle4 = new Vehicle("mercedez", 4);
        try {
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);//1
            parkingLot.vehicleParking(vehicle2, DriverType.NORMAL, CarType.SMALL);//2
            parkingLot.vehicleParking(vehicle3, DriverType.NORMAL, CarType.SMALL);//3
            parkingLot.vehicleUnparking(vehicle1);//1--
            parkingLot.vehicleUnparking(vehicle3);//3--
            parkingLot.vehicleParking(vehicle4, DriverType.NORMAL, CarType.SMALL); // parked at slot 1
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            Assert.assertEquals(3, parkingLot.getVehicleLocation(vehicle1));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenDriverTypeMentioned_ShouldParkAccordingly() {
        Vehicle vehicle1 = new Vehicle("alto", 1);
        Vehicle vehicle2 = new Vehicle("brezza", 2);
        Vehicle vehicle3 = new Vehicle("etios", 3);

        try {
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);
            parkingLot.vehicleParking(vehicle2, DriverType.HANDICAP, CarType.SMALL);
            parkingLot.vehicleParking(vehicle3, DriverType.HANDICAP, CarType.SMALL);
            int key1 = parkingLot.getVehicleLocation(vehicle1);//6
            int key2 = parkingLot.getVehicleLocation(vehicle2);//1
            int key3 = parkingLot.getVehicleLocation(vehicle3);//1
            Assert.assertEquals(6, key1);
            Assert.assertEquals(1, key2);
            Assert.assertEquals(2, key3);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTwoParkingMaps_WhenLargeVehicleComes_ShouldParkAtLotHavingLargeSpace() {
        Vehicle vehicle1 = new Vehicle("alto", 1);
        Vehicle vehicle2 = new Vehicle("brezza", 2);
        Vehicle vehicle3 = new Vehicle("etios", 3);
        try {
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);//6
            parkingLot.vehicleParking(vehicle2, DriverType.HANDICAP, CarType.SMALL);//1
            parkingLot.vehicleParking(vehicle3, DriverType.HANDICAP, CarType.LARGE);//1
            int key1 = parkingLot.getVehicleLocation(vehicle1);//6
            int key2 = parkingLot.getVehicleLocation(vehicle2);//1
            int key3 = parkingLot.getVehicleLocation(vehicle3);//1
            Assert.assertEquals(6, key1);
            Assert.assertEquals(1, key2);
            Assert.assertEquals(1, key3);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
