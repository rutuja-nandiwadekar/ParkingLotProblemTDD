package com.blz;

import java.util.Map;

public class Owner implements ParkingLotObserver {
    private static String status;
    private int key;

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
     * @param parkingMap
     * @return returns key to attendant
     */
    public int getLotNumberToPark(Map<Integer, Vehicle> parkingMap) {
        for (Map.Entry map : parkingMap.entrySet()) {
            if (map.getValue() == null) {
                this.key = (int) map.getKey();
                break;
            }
        }
        return this.key;
    }
}
