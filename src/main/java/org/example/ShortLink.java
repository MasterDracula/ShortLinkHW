package org.example;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ShortLink {
    private final Map<String, Data> URLMap = new ConcurrentHashMap<>();
    private final SecureRandom RandomGen = new SecureRandom();
    private final long timeOfLife = TimeUnit.MINUTES.toMillis(5);

    String ShortingURL (String longURL, String userID) {
        String shortURL;
        do {
            shortURL = generatorShortURL();
        } while (URLMap.containsKey(shortURL));
        URLMap.put(shortURL, new Data(longURL, userID));
        return shortURL;
    }

    String generatorShortURL(){
        byte[]sizeURL = new byte[9];
        RandomGen.nextBytes(sizeURL);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(sizeURL);
    }

    void deleteURL(){
        long newTime = System.currentTimeMillis();
        URLMap.entrySet().removeIf(entry -> (newTime - entry.getValue().getCreationTime()) > timeOfLife);
    }
}