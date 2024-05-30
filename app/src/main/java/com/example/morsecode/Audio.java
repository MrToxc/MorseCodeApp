package com.example.morsecode;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class is used to play morse code via audio.
 * It also writes progress on screen
 * Before running the playTone() method, following variables need to be specified:
 * speedDivider - Through Setter
 * startingDotLength - Through Constants class
 * progressText - Through setter
 * arrayList - Through setter
 */

public class Audio implements Runnable {
    final double startingDotLength = Constants.dotLengthS;
    private ArrayList<MorseCodeSymbols> morseCode = new ArrayList<>();
    private int speedDivider;
    private boolean rdyForNext = true;
    private boolean stopped = false;
    private TextView progressText;

    public void setProgressText(TextView progressText) {
        this.progressText = progressText;
    }

    public boolean isRdyForNext() {
        return rdyForNext;
    }

    public void setRdyForNext(boolean rdyForNext) {
        this.rdyForNext = rdyForNext;
    }

    public void setSpeedDivider(int speedDivider) {
        this.speedDivider = speedDivider;
    }

    public void setMorseCode(ArrayList<MorseCodeSymbols> morseCode) {
        this.morseCode = morseCode;
    }

    /**
     * Method used to flash message. It is using arrayList of morseCodeSymbols as input
     * @throws InterruptedException
     */
    public void playTone() throws InterruptedException {
        stopped = false;
        double dotLength = startingDotLength / speedDivider;
        double linelength = dotLength * 3;
        double separatorLength = linelength * 1000;
        double spaceLength = dotLength * 7 * 1000;
        double periodLenght = dotLength * 10 * 1000;
        boolean canBePlayed = false;
        // Buffer size in bytes
        int sampleRate = 44100;

        //Next 9 lines are from chatGPT
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
                double freqOfTone = Constants.frequency; // Frequency (Hz)

        progressText.setText("");
        for (int l = 0; l < morseCode.size(); l++) {
            if (stopped) {
                break;
            }
            progressText.setText(progressText.getText().toString() + morseCode.get(l).toString());
            switch (morseCode.get(l)) {
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
            //This is also from chatGPT
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
        stopped = false;
    }

    public void stop() {
        stopped = true;
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
