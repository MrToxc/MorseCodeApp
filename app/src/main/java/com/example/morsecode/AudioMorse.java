package com.example.morsecode;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.util.ArrayList;

public class AudioMorse implements Runnable {
    final double statingDotLength = 0.4;
    private ArrayList<MorseCodeSymbols> finalArray = new ArrayList<>();
    private int speedDivider;
    private boolean rdyForNext = true;

    public boolean isRdyForNext() {
        return rdyForNext;
    }

    public void setRdyForNext(boolean rdyForNext) {
        this.rdyForNext = rdyForNext;
    }

    public void setSpeedDivider(int speedDivider) {
        this.speedDivider = speedDivider;
    }

    public void setFinalArray(ArrayList<MorseCodeSymbols> finalArray) {
        this.finalArray = finalArray;
    }

    public void playTone() throws InterruptedException {
        double dotLength = statingDotLength / speedDivider;
        double linelength = dotLength * 3;
        double separatorLength = linelength * 1000;
        double spaceLength = dotLength * 7 * 1000;
        double periodLenght = dotLength * 10 * 1000;
        boolean canBePlayed = false;
        // Buffer size in bytes
        int sampleRate = 44100;

        //This part is from chatGPT
        int bufferSize = AudioTrack.getMinBufferSize(sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_8BIT);
        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_8BIT,
                bufferSize,
                AudioTrack.MODE_STREAM);
        // Sample rate (Hz)
        // Sine wave parameters
                double duration = 0; // seconds
                double freqOfTone = 240; // Frequency (Hz)



        for (int l = 0; l < finalArray.size(); l++) {
            switch (finalArray.get(l)) {
                case DOT -> {
                    duration = dotLength;
                    canBePlayed = true;
                }
                case LINE -> {
                    duration = linelength;
                    canBePlayed = true;
                }
                case SEPARATOR -> {
                    Thread.sleep((int) separatorLength);
                    canBePlayed = false;
                }
                case SPACE -> {
                    Thread.sleep((int) spaceLength);
                    canBePlayed = false;
                }
                case PERIOD -> {
                    Thread.sleep((int) periodLenght);
                    canBePlayed = false;
                }
            }
            if (canBePlayed) {
                int numSamples = (int) (duration * sampleRate);
                byte[] generatedSnd = new byte[2 * numSamples];
                for (int i = 0; i < numSamples; ++i) {
                    double angle = 2 * Math.PI * i / (sampleRate / freqOfTone);
                    generatedSnd[i] = (byte) (Math.sin(angle) * 127);
                }
            audioTrack.play();
            audioTrack.write(generatedSnd, 0, generatedSnd.length);
            audioTrack.stop();
            Thread.sleep((int) dotLength);
            }
        }
            audioTrack.release();
            rdyForNext = true;
    }

    @Override
    public void run() {
        try {
            playTone();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
