package com.java.Timer;

public class Timer implements AbstractTimer {
    long startTime;
    long endTime;

    public Timer() {
        startTime = 0;
        endTime = 0;
    }

    @Override
    public void start() {
        startTime = System.nanoTime();
    }

    @Override
    public void end() {
        endTime = System.nanoTime();
    }

    @Override
    public long getTime() {
        return endTime - startTime;
    }

    @Override
    public long endAndGetTime() {
        end();
        return getTime();
    }
}
