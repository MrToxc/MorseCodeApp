package com.example.morsecode;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class is used to play morse code via camera flash.
 * It also writes progress on screen
 * Before running the flashMessage() method, following variables need to be specified:
 * speedDivider - Through Setter
 * startingDotLength - Through Constants class
 * progressText - Through setter
 * cameraManager - Through setter
 * arrayList - Through setter
 */

public class Flash implements Runnable {
    private CameraManager cameraManager;
    private TextView progressText;
    private boolean rdyForNext = true;
    private ArrayList<MorseCodeSymbols> morseCode = new ArrayList<>();

    private int speedDivider;
    private boolean stopped = false;

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

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    /**
     * Method used to flash message. It is using arrayList of morseCodeSymbols as input
     * @throws InterruptedException
     */
    public void flashMessage() throws InterruptedException {
        stopped = false;
        int statingDotLength = Constants.dotLengthMs;
        int dotLength = statingDotLength / speedDivider;
        int linelength = dotLength * 3;
        int separatorLength = dotLength * 3;
        int spaceLength = dotLength * 7;
        int periodLenght = dotLength * 10;

        progressText.setText("");
        for (int i = 0; i < morseCode.size(); i++) {
            if (stopped) {
                break;
            }
            progressText.setText(progressText.getText().toString() + morseCode.get(i).toString());
            if (morseCode.get(i) == MorseCodeSymbols.LINE || morseCode.get(i) == MorseCodeSymbols.DOT) {
                Thread.sleep(dotLength);
            }
            switch (morseCode.get(i)) {
                case DOT -> {
                    flashLightOn();
                    Thread.sleep(dotLength);
                    flashLightOff();
                }
                case LINE -> {
                    flashLightOn();
                    Thread.sleep(linelength);
                    flashLightOff();
                }
                case SEPARATOR -> Thread.sleep(separatorLength);
                case SPACE -> Thread.sleep(spaceLength);
                case PERIOD -> Thread.sleep(periodLenght);
            }
        }
        stopped = false;
        rdyForNext = true;
    }


    public void stop() {
        stopped = true;
    }


    //borrowed from https://github.com/msindev/FlashLight
    private void flashLightOn() {
        CameraManager cameraManager = this.cameraManager;
        try {
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
        } catch (CameraAccessException e) {
            Log.e("Camera Problem", "Cannot turn on camera flashlight");
        }
    }

    //borrowed from https://github.com/msindev/FlashLight
    private void flashLightOff() {
        CameraManager cameraManager = this.cameraManager;
        try {
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
            Log.e("Camera Problem", "Cannot turn off camera flashlight");
        }
    }


    @Override
    public void run() {
        try {
            flashMessage();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
