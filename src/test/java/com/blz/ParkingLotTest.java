package com.blz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    @Before
    public void setUp() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
    }

    //UC1 = Ability To Park vehicle
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("alto");
        ParkingLot parkingLot;
        boolean isParked = parkingLot.park(vehicle);
        Assert.assertTrue(isParked);
    }

    //UC : Ability to unpark Vehicle
    @Test
    public void givenVehicle_WhenUnparked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("alto");
        parkingLot.park(vehicle);
        boolean isUnParked = parkingLot.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }
}
