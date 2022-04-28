package com.blz;

import java.util.Map;

public class Attendant {
    Owner owner = new Owner();
    public int parkTheVehicle(Map<Integer, Vehicle> parkingMap) {
        return owner.getLotNumberToPark(parkingMap);
    }
}
