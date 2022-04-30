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
//        for (Map.Entry map : parkingMap.entrySet()) {
//            if (map.getValue() == null) {
//                this.key = (int) map.getKey();
//                break;
//            }
//        }

        if(driverType==DriverType.HANDICAP)
            for(int key=1;key<=parkingMap.size();key++){
                if(parkingMap.get(key)==null)
                    return key;
            }

        if(driverType==DriverType.NORMAL) {
            int key = 6;
            for ( key = 6; key <= parkingMap.size(); key++) {
                if (parkingMap.get(key)==null)
                    return key;
            }

            for ( key = 1; key <= parkingMap.size(); key++) {
                if (parkingMap.get(key)==null)
                    return key;
            }
        }
        return this.key;
    }
}
