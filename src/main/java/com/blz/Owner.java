package com.blz;

import java.util.Map;

public class Owner implements ParkingLotObserver {
    private static String status;
    private int key=0;

    //updating message to owner
    public void update(String message) {
        this.status = message;
    }

    /**
     * @return updated message
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @Purpose : Assign unique key for each vehicle
     * @Param : map (parkingmap)
     * @Function :To assign unique key for each vehicle
     * @Return :Key to attendant from map
     */
    public int getLotNumberToPark(Map<Integer, Vehicle> parkingMap, DriverType driverType) {

        if (driverType == DriverType.NORMAL) {
            int normalKey =6;
            for ( normalKey = 6; normalKey <= parkingMap.size(); normalKey++) {
                if (parkingMap.get(normalKey) == null)
                    return normalKey;
            }
        }
        if (driverType == DriverType.HANDICAP)
            for (int key = 1; key <= parkingMap.size(); key++) {
                if (parkingMap.get(key) == null)
                    return key;
            }
        if (driverType == DriverType.NORMAL) {
            int normalKey = 1;
            for ( normalKey=1; normalKey <= parkingMap.size(); normalKey++) {
                if (parkingMap.get(normalKey) == null)
                    return normalKey;
            }
        }
        return this.key;
    }
}
