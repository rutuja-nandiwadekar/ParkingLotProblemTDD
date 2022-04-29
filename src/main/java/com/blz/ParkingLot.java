package com.blz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static final int MAX_LOT_CAPACITY = 4;
    private Map<Integer,Vehicle> parkingMap = new LinkedHashMap<>();
    private List<ParkingLotObserver> observers;
    Attendant attendant;
    private LocalDateTime time;



    public ParkingLot() {
        this.observers = new ArrayList<>();
        attendant = new Attendant();
        for(int i=1;i<=MAX_LOT_CAPACITY;i++){
            parkingMap.put(i,null);
        }
    }

    /**
     * @Purpose : To park the vehicle
     * @Param : vehicle
     */
    public void vehicleParking(Vehicle vehicle) throws ParkingLotException {
        if (this.parkingMap.size() == MAX_LOT_CAPACITY && !parkingMap.containsValue(null))
            throw new ParkingLotException("Parking lot is full");
        if(this.parkingMap.containsValue(null)){
            int key = attendant.parkTheVehicle(parkingMap);
            this.parkingMap.put(key,vehicle);
            setParkTime(LocalDateTime.now());
        }

        if(this.parkingMap.size()== MAX_LOT_CAPACITY && !parkingMap.containsValue(null)){
            String message = "Parking lot is full";
            for(ParkingLotObserver observer:observers){
                observer.update(message);
            }
        }
    }

    /**
     * @Purpose : Method to check vehicle is park or not
     * @Param : vehicle
     * @Return : Returns boolean value true or false
     */
    public boolean isParked(Vehicle vehicle) {
        if (this.parkingMap.containsValue(vehicle))
            return true;
        return false;
    }

    /**
     *
     * @param time
     */
    public void setParkTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getParkedTime() {
        return this.time;
    }
    /**
     * @Purpose : To unPark the vehicle
     * @Param : vehicle
     */
    public void vehicleUnparking(Vehicle vehicle) throws ParkingLotException {
        int key=0;
        int nullCount=0;
//        if (this.parkingMap.isEmpty())
        for(Map.Entry map : parkingMap.entrySet()){
            if(map.getValue()==null) nullCount++;
        }
        if(nullCount==MAX_LOT_CAPACITY)
            throw new ParkingLotException("lot is empty");
        if (this.parkingMap.containsValue(vehicle)){
            for(Map.Entry map : parkingMap.entrySet()){
                if(map.getValue()==vehicle) key= (int) map.getKey();
            }
            this.parkingMap.put(key,null);
            for(ParkingLotObserver observer:observers){
                observer.update("Parking lot has space");
            }
            return;
        }
        throw new ParkingLotException("Please ask correct vehicle");
    }

    /**
     * @Purpose :  Method to check vehicle is unpark or not
     * @Param : vehicle
     * @Return : Returns boolean value true or false
     */
    public boolean isUnParked(Vehicle vehicle) {
        if (!this.parkingMap.containsValue(vehicle))
            return true;
        return false;
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public int getVehicleLotNumber(Vehicle vehicle) {
        for (Map.Entry map : parkingMap.entrySet()){
            if(map.getValue()==vehicle)
                return (int) map.getKey();
        }
        return 0;
    }

    public int getVehicleLocation(Vehicle vehicle) {
        return getVehicleLotNumber(vehicle);
    }


}



