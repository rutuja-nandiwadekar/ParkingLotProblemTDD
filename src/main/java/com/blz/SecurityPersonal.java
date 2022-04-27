package com.blz;

public class SecurityPersonal {
    private static String status;

    //updating message to security personal
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
