package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Data {
    private final String longURL;
    private final String userID;
    private final AtomicInteger cliker = new AtomicInteger();
    private final long timeOfLife = System.currentTimeMillis();

    public Data(String longURL, String userID) {
        this.longURL = longURL;
        this.userID = userID;
    }

    public String getLongURL() {
        return longURL;
    }

    public String getUserID() {
        return userID;
    }


    public long getCreationTime() {
        return timeOfLife;
    }
}
