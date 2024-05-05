package com.example.morsecode;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Flash {
//    MainActivity mainActivity = new MainActivity();
    private CameraManager cameraManager;
    private int statingDotLength = 600;

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    public void flashMessage(ArrayList<MorseCodeSymbols> arr, int speedDivider) throws InterruptedException {

        int dotLength = statingDotLength / speedDivider;
        int linelength = dotLength * 3;
        int separatorLength = dotLength * 3;
        int spaceLength = dotLength * 7;
        int periodLenght = dotLength * 10;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == MorseCodeSymbols.LINE || arr.get(i) == MorseCodeSymbols.DOT) {
                Thread.sleep(separatorLength);
            }
            switch (arr.get(i)) {
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
                case SPACE -> Thread.sleep(spaceLength);
                case PERIOD -> Thread.sleep(periodLenght);
            }
        }
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




}
