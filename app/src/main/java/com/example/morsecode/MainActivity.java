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

    private TextView textViewHeading;
    private TextView inputLabel;
    private TextView morseCodeLabel;
    private TextView progressLabel;
    private Spinner spinner;
//    private boolean hasCameraFlash = false;
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
        textViewHeading = findViewById(R.id.textViewHeading);
        inputLabel = findViewById(R.id.inputLabel);
        morseCodeLabel = findViewById(R.id.morseCodeLabel);
        progressLabel = findViewById(R.id.progressLabel);
        flash.setCameraManager((CameraManager) getSystemService(Context.CAMERA_SERVICE));
        translator.setMorseCodeView(findViewById(R.id.morseCodeView));
        setEnglish();

        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.speedModifiers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        //doest work
//        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        input = findViewById(R.id.input);

        buttonFlash.setOnClickListener(v -> {
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

        switchLenguage.setOnClickListener(v -> {
            languageBoolean = !languageBoolean;
            if (languageBoolean) {
                setCzech();
            } else setEnglish();
        });
    }

    private void setEnglish() {
        textViewHeading.setText("Morse Code Transiver");
        inputLabel.setText("Your message");
        buttonFlash.setText("Flash");
        buttonAudio.setText("Audio");
        buttonStop.setText("Stop");
        morseCodeLabel.setText("morse code");
        progressLabel.setText("progress");
    }

    private void setCzech() {
        textViewHeading.setText("Vysílač Morseovy Abecedy");
        inputLabel.setText("Vaše zpráva");
        buttonFlash.setText("Světlo");
        buttonAudio.setText("Zvuk");
        buttonStop.setText("Zastavit");
        morseCodeLabel.setText("morseova abeceda");
        progressLabel.setText("postup");
    }
}