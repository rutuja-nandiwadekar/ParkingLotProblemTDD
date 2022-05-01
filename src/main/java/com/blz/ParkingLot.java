package com.blz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private static final int MAX_LOT_CAPACITY = 10;
    private Map<Integer,Vehicle> parkingMap = new LinkedHashMap<>();
    private final Map<Integer,Vehicle> parkingMap1 = new LinkedHashMap<>();
    private final Map<Integer,Vehicle> parkingMap2 = new LinkedHashMap<>();
    private List<ParkingLotObserver> observers;
    Attendant attendant;
    private LocalDateTime time;


    /**
     * @Purpose : Parking Lot Initialisation
     * @Param :Vehicle , parking lot capacity
     * @Function : Array , Hashmap
     * @Return : NA
     */
    public ParkingLot() {
        this.observers = new ArrayList<>();
        attendant = new Attendant();
        for(int i=1;i<=MAX_LOT_CAPACITY;i++){
            parkingMap1.put(i,null);
        }

        for(int i=1;i<=MAX_LOT_CAPACITY;i++){
            parkingMap2.put(i,null);
        }
    }

    /**
     * @Purpose :To park Vehicle as per availability by attendant
     * @Param : map,exception handling, key value , Date time
     * @Function :To check for parking status
     * @Return : Local date & Time
     */
    public void vehicleParking(Vehicle vehicle, DriverType driverType, CarType carType) throws ParkingLotException {
        if(carType==CarType.SMALL)
            parkingMap=parkingMap1;
        if(carType==CarType.LARGE)
            parkingMap=parkingMap2;
        if (this.parkingMap.size() == MAX_LOT_CAPACITY && !parkingMap.containsValue(null))
            throw new ParkingLotException("Parking lot is full");
        if(this.parkingMap.containsValue(null)){
            int key = attendant.parkTheVehicle(parkingMap,driverType);
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
     * @Purpose :To check for parked vehicle (car)
     * @Param :boolean
     * @Function :To park and assign status
     * @Return :t/f
     */
    public boolean isParked(Vehicle vehicle) {
        if(parkingMap1.containsValue(vehicle))
            parkingMap=parkingMap1;
        if(parkingMap2.containsValue(vehicle))
            parkingMap=parkingMap2;
        if (this.parkingMap.containsValue(vehicle))
            return true;
        return false;
    }


    /**
     * @Purpose :Set & get parking time for parking charges
     * @Param :Date and time
     * @Function :To get time for calculating charges
     * @Return :
     */
    public void setParkTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getParkedTime() {
        return this.time;
    }


    /**
     * @Purpose :To unpark vehicle (car)
     * @Param :map,observer,array
     * @Function :To initiate sequence parking
     * @Return :key value matches
     */
    public void vehicleUnparking(Vehicle vehicle) throws ParkingLotException {
        if(parkingMap1.containsValue(vehicle))
            parkingMap=parkingMap1;
        if(parkingMap2.containsValue(vehicle))
            parkingMap=parkingMap2;
        int key=0;
        int nullCount=0;
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
     * @Purpose : Method to check vehicle is unpark or not
     * @Param : vehicle
     * @Return : Returns boolean value true or false
     */
    public boolean isUnParked(Vehicle vehicle) {
        if(parkingMap1.containsValue(vehicle))
            parkingMap=parkingMap1;
        if(parkingMap2.containsValue(vehicle))
            parkingMap=parkingMap2;
        if (!this.parkingMap.containsValue(vehicle))
            return true;
        return false;
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public int getVehicleLotNumber(Vehicle vehicle) {
        if(parkingMap1.containsValue(vehicle))
            parkingMap=parkingMap1;
        if(parkingMap2.containsValue(vehicle))
            parkingMap=parkingMap2;
        for (Map.Entry map : parkingMap.entrySet()){
            if(map.getValue()==vehicle)
                return (int) map.getKey();
        }
        return 0;
    }

    public int getVehicleLocation(Vehicle vehicle) {
        return getVehicleLotNumber(vehicle);
    }

    /**
     * @Purpose : Method to find vehicles of same colour
     * @param color
     * @return returns the vehicle list of same colour
     */
    public List<Vehicle> getVehicleByColor(String color) {
        List<Vehicle> carList = mapValuesTolist(parkingMap1);
        return carList.stream()
                .filter(car->car!=null && car.getColor().equals(color))
                .collect(Collectors.toList());
    }
    /**
     * @Purpose : Method to find the lot no of vehicles of same colour
     * @param color
     * @return returns the lotNumber list of  same color
     */
    public List<Integer> getVehicleLotNumberByColor(String color) {
        List<Integer> lotList = new ArrayList<>();
        List<Vehicle> carList = new ArrayList<>();
        for(int key=1;key<=parkingMap1.size();key++){
            if(parkingMap1.get(key)!=null)
                if(parkingMap1.get(key).getColor()==color)
                    lotList.add(key);
        }
        return lotList;
    }

    /**
     * @Purpose : method to convert map to list
     * @param map
     * @return
     */
    static public List<Vehicle> mapValuesTolist(Map<Integer,Vehicle> map){

        return new ArrayList<>(map.values());
    }


    /**
     * @param modelName, colour
     * @return list of lot number of cars by using color and modelName
     */
    public List<Integer> getVehicleLotNumberByColorAndModelName(String color, String name) {
        List<Integer> lotList = new ArrayList<>();
        for (int key = 1; key <= parkingMap1.size(); key++) {
            if (parkingMap1.get(key) != null)
                if (parkingMap1.get(key).getColor() == color && parkingMap1.get(key).getName() == name)
                    lotList.add(key);
        }
        return lotList;
    }

    public List<String> getVehicleNumberPlate(List<Integer> lotNumberList) {
        List<String> numPlateList = new ArrayList<>();
        for (int i = 0; i < lotNumberList.size(); i++) {
            numPlateList.add(parkingMap1.get(lotNumberList.get(i)).getPlateNumber());
        }
        return numPlateList;
    }

    public String getVehicleNumberPlateBylotNumber(int key) {
        return parkingMap1.get(key).getPlateNumber();
    }
}



