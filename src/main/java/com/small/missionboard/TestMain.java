package com.small.missionboard;


import java.time.Duration;
import java.time.LocalDateTime;

public class TestMain {
    public static void main(String[] args) {
        LocalDateTime previous = LocalDateTime.now();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long delta = Duration.between(LocalDateTime.now(), previous).toMillis();
        System.out.println(delta);

    }
}
