package org.example;

import java.util.concurrent.TimeUnit;

public class URLTime {
    private final long timeOfLife = TimeUnit.MINUTES.toMillis(5);
    // получение текущего времени
    private long currentTime;

    public URLTime() {
        this.currentTime = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return currentTime + timeOfLife < System.currentTimeMillis();
    }

    public void updateTime() {
        this.currentTime = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        URLTime urlTime = new URLTime();
        System.out.println("Initial URLTime: " + urlTime);
        TimeUnit.SECONDS.sleep(10); // ждем 10 секунд
        urlTime.updateTime();
        System.out.println("Updated URLTime: " + urlTime);
        TimeUnit.SECONDS.sleep(15); // ждем еще 15 секунд
        System.out.println("Expired? " + urlTime.isExpired());
    }
}