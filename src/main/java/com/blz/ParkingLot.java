package com.blz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static final int MAX_LOT_CAPACITY = 2;
    private Map<Integer,Vehicle> parkingMap;
    private List<ParkingLotObserver> observers;
    Attendant attendant;
    private LocalDateTime time;



    public ParkingLot() {
        this.observers = new ArrayList<>();
        parkingMap = new LinkedHashMap<>();
        attendant = new Attendant();
    }

    /**
     * @Purpose : To park the vehicle
     * @Param : vehicle
     */
    public void vehicleParking(Vehicle vehicle) throws ParkingLotException {
        if (this.parkingMap.size() == MAX_LOT_CAPACITY)
            throw new ParkingLotException("Parking lot is full");
        if(this.parkingMap.size()<MAX_LOT_CAPACITY){
            int key = attendant.parkTheVehicle(parkingMap);
            this.parkingMap.put(key,vehicle);
            setParkTime(LocalDateTime.now());
        }

        if(this.parkingMap.size()== MAX_LOT_CAPACITY){
            String message = "Parking lot is full";
            for(ParkingLotObserver observer:observers){
                observer.update(message);
            }
        }
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
        if (this.parkingMap.isEmpty())
            throw new ParkingLotException("lot is empty");
        if (this.parkingMap.containsValue(vehicle)){
            for(Map.Entry map : parkingMap.entrySet()){
                if(map.getValue()==vehicle) key= (int) map.getKey();
            }
            this.parkingMap.remove(key);
            for(ParkingLotObserver observer:observers){
                observer.update("Parking lot has space");
            }
            return;
        }
        throw new ParkingLotException("Please ask correct vehicle");
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



