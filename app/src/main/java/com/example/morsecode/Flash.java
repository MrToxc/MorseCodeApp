package com.example.morsecode;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;

import java.util.ArrayList;

public class Flash implements Runnable{
    private CameraManager cameraManager;

    private boolean rdyForNext = true;
    private ArrayList<MorseCodeSymbols> finalArray = new ArrayList<>();

    private int speedDivider;
    private boolean stopped = false;

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

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    public void flashMessage() throws InterruptedException {
        stopped = false;
        int statingDotLength = Constants.dotLengthMs;
        int dotLength = statingDotLength / speedDivider;
        int linelength = dotLength * 3;
        int separatorLength = dotLength * 3;
        int spaceLength = dotLength * 7;
        int periodLenght = dotLength * 10;

        for (int i = 0; i < finalArray.size(); i++) {
            if (stopped) {
                break;
            }
            if (finalArray.get(i) == MorseCodeSymbols.LINE || finalArray.get(i) == MorseCodeSymbols.DOT) {
                Thread.sleep(dotLength);
            }
            switch (finalArray.get(i)) {
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
