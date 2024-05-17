package com.example.morsecode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonFlash;
    private Button buttonSlow;
    private Button buttonMedium;
    private Button buttonFast;
    private Button buttonAudio;
    private Switch language;

    private EditText input;
    private TextView speed;
    private boolean hasCameraFlash = false;
    private boolean languageBoolean = false;
    private boolean readyForNext = true;
    private boolean currentlyPlaying = false;
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
        language = findViewById(R.id.switchLanguage);
        flash.setCameraManager((CameraManager) getSystemService(Context.CAMERA_SERVICE));

        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        input = findViewById(R.id.input);
        speed = findViewById(R.id.speedIndicator);

        buttonFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraFlash) {
                    if (flash.isRdyForNext()) {
                        flash.setRdyForNext(false);
                        if (languageBoolean) {
                            flash.setFinalArray(translator.getArrRdy(stringPreparer.getCzech(stringPreparer.getStringRdy(String.valueOf(input.getText())))));
                        } else flash.setFinalArray(translator.getArrRdy(stringPreparer.getStringRdy(String.valueOf(input.getText()))));
                        flash.setSpeedDivider(speedDivider);
                        Thread thread = new Thread(flash);
                        thread.start();
                    }
                } else {
                    //getCzech?
                    //flashing screen
                }
            }
        });

        //it keeps crashing, i need to limit the number of messages beeing played to one
        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audioMorse.isRdyForNext()) {
                    audioMorse.setRdyForNext(false);
                    if (languageBoolean) {
                    audioMorse.setFinalArray(translator.getArrRdy(stringPreparer.getCzech(stringPreparer.getStringRdy(String.valueOf(input.getText())))));
                    } else audioMorse.setFinalArray(translator.getArrRdy(stringPreparer.getStringRdy(String.valueOf(input.getText()))));
                    audioMorse.setSpeedDivider(speedDivider);
                    Thread thread = new Thread(audioMorse);
                    thread.start();
                }
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            //this method is here, because .isActivated() somehow doesnt works
            @Override
            public void onClick(View v) {
                if (languageBoolean) {
                    languageBoolean = false;
                } else languageBoolean = true;
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
    }


}