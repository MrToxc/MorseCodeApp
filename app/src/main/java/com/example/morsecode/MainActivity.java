package com.example.morsecode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonFlash;
    private Button buttonSlow;
    private Button buttonMedium;
    private Button buttonFast;
    private Button buttonAudio;

    private EditText input;
    private TextView speed;
    private boolean hasCameraFlash = false;
    private int speedDivider = 2;
    Flash flash = new Flash();
    Translator translator = new Translator();
    StringPreparer stringPreparer = new StringPreparer();
    AudioMorse audioMorse = new AudioMorse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFlash = findViewById(R.id.button);
        buttonAudio = findViewById(R.id.buttonAudio);
        buttonSlow = findViewById(R.id.buttonSlow);
        buttonMedium = findViewById(R.id.buttonMedium);
        buttonFast = findViewById(R.id.buttonFast);
        flash.setCameraManager((CameraManager) getSystemService(Context.CAMERA_SERVICE));

        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        input = findViewById(R.id.input);
        speed = findViewById(R.id.speedIndicator);

        buttonFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraFlash) {
                    try {
                        flash.flashMessage(translator.getArrRdy(stringPreparer.getStringRdy(String.valueOf(input.getText()))), speedDivider);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    //flashing screen
                }
            }
        });



        buttonSlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speed.setText("Slow");
                speedDivider = 1;
            }
        });
        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speed.setText("Medium");
                speedDivider = 2;
            }
        });
        buttonFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speed.setText("Fast");
                speedDivider = 4;
            }
        });
        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    audioMorse.playTone(translator.getArrRdy(stringPreparer.getStringRdy(String.valueOf(input.getText()))), speedDivider);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}