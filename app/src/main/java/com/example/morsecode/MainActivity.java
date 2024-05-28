package com.example.morsecode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonFlash;
    private Button buttonAudio;
    private Button buttonStop;
    private Switch switchLenguage;
    private EditText input;
    private TextView progressText;
    private Spinner spinner;
    private boolean hasCameraFlash = false;
    private boolean languageBoolean = false;
    Flash flash = new Flash();
    Translator translator = new Translator();
    StringPreparer stringPreparer = new StringPreparer();
    Audio audio = new Audio();

    private int getSpeedDivider() {
        return switch (spinner.getSelectedItem().toString()) {
            case "slow" -> 1;
            case "medium" -> 2;
            case "fast" -> 4;
            default -> throw new IllegalStateException("Unknown speed" + spinner.getPrompt());
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        buttonFlash = findViewById(R.id.buttonFlash);
        buttonAudio = findViewById(R.id.buttonAudio);
        buttonStop = findViewById(R.id.buttonStop);
        switchLenguage = findViewById(R.id.switchLanguage);
        progressText = findViewById(R.id.progressText);
        flash.setCameraManager((CameraManager) getSystemService(Context.CAMERA_SERVICE));

        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.speedModifiers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        input = findViewById(R.id.input);

        buttonFlash.setOnClickListener(v -> {
            if (hasCameraFlash) {
                if (flash.isRdyForNext() && audio.isRdyForNext()) {
                    flash.setRdyForNext(false);
                    if (languageBoolean) {
                        flash.setMorseCode(translator.stringToMorseCode(stringPreparer.getCzech(stringPreparer.getStringRdy(String.valueOf(input.getText())))));
                    } else flash.setMorseCode(translator.stringToMorseCode(stringPreparer.getStringRdy(String.valueOf(input.getText()))));
                    flash.setSpeedDivider(getSpeedDivider());
                    flash.setProgressText(progressText);
                    Thread thread = new Thread(flash);
                    thread.start();
                }
            } else {
                //hasCameraFlash is showing true, when it shoudnt
                return;
                //getCzech?
                //flashing screen
            }
        });

        buttonAudio.setOnClickListener(v -> {
            if (flash.isRdyForNext() && audio.isRdyForNext()) {
                audio.setRdyForNext(false);
                if (languageBoolean) {
                audio.setMorseCode(translator.stringToMorseCode(stringPreparer.getCzech(stringPreparer.getStringRdy(String.valueOf(input.getText())))));
                } else audio.setMorseCode(translator.stringToMorseCode(stringPreparer.getStringRdy(String.valueOf(input.getText()))));
                audio.setSpeedDivider(getSpeedDivider());
                audio.setProgressText(progressText);
                Thread thread = new Thread(audio);
                thread.start();
            }
        });

        buttonStop.setOnClickListener(v -> {
            flash.stop();
            audio.stop();
        });

        switchLenguage.setOnClickListener(v -> languageBoolean = !languageBoolean);






    }
}