package com.blz;

public class Owner implements ParkingLotObserver{
    private static String status;

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
}
