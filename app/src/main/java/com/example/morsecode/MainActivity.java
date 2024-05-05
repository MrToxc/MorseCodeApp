package com.example.morsecode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button buttonSlow;
    private Button buttonMedium;
    private Button buttonFast;

    private EditText input;
    private TextView speed;
    private boolean hasCameraFlash = false;
    private int speedDivider = 2;
    Flash flash = new Flash();
    Translator translator = new Translator();
    StringPreparer stringPreparer = new StringPreparer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        buttonSlow = findViewById(R.id.buttonSlow);
        buttonMedium = findViewById(R.id.buttonMedium);
        buttonFast = findViewById(R.id.buttonFast);
        flash.setCameraManager((CameraManager) getSystemService(Context.CAMERA_SERVICE));

        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        input = findViewById(R.id.input);
        speed = findViewById(R.id.speedIndicator);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    flash.flashMessage(translator.getArrRdy(stringPreparer.getStringRdy(input.toString())), speedDivider);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
    }

}