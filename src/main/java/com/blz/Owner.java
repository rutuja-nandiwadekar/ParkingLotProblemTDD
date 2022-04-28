package com.blz;

import java.util.Map;

public class Owner implements ParkingLotObserver{
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
     *
     * @param parkingMap
     * @return returns key to attendant
     */
    public int getLotNumberToPark(Map<Integer, Vehicle> parkingMap) {
        int lotNumber=1;
        if(parkingMap.isEmpty()) this.key = lotNumber;

        for(Map.Entry map :parkingMap.entrySet()){
            lotNumber++;
        }
        this.key=lotNumber;
        return this.key;
    }
}
