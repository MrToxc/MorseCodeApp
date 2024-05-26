package com.example.morsecode;

public class Constants {
    //length in ms
    private final int dotLengthMs = 400;
    //length in s
    private final double dotLengthS = (double) dotLengthMs / 1000.0;
    //frequency in Hz
    private final int frequency = 240;


    public int getDotLengthMs() {
        return dotLengthMs;
    }

    public int getFrequency() {
        return frequency;
    }

    public double getDotLengthS() {
        return dotLengthS;
    }
}
