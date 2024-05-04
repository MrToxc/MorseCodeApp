package com.example.morsecode;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Flash {
    MainActivity mainActivity = new MainActivity();
    private int dotLength = 400;
    private int linelength = dotLength * 3;
    private int separatorLength = dotLength;
    private int spaceLength = dotLength * 7;
    private int periodLenght = dotLength * 10;


    public void flashMessage(ArrayList<MorseCodeSymbols> arr, int speedDivider) throws InterruptedException {

        dotLength = dotLength / speedDivider;
        linelength = dotLength * 3;
        separatorLength = dotLength;
        spaceLength = dotLength * 7;
        periodLenght = dotLength * 10;

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
        CameraManager cameraManager = (CameraManager) mainActivity.getSystemService(Context.CAMERA_SERVICE);
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
        CameraManager cameraManager = (CameraManager) mainActivity.getSystemService(Context.CAMERA_SERVICE);
        try {
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
            Log.e("Camera Problem", "Cannot turn off camera flashlight");
        }
    }




}
