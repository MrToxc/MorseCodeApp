package com.example.morsecode;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class AudioMorse {

    //stole that from chatGPT, donno what it does
    public void playTone() {
        // Sample rate (Hz)
        int sampleRate = 44100;
        // Buffer size in bytes
        int bufferSize = AudioTrack.getMinBufferSize(sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_8BIT);

        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_8BIT,
                bufferSize,
                AudioTrack.MODE_STREAM);

        // Sine wave parameters
        double freqOfTone = 440; // Frequency (Hz)
        double duration = 1.0; // seconds

        int numSamples = (int) (duration * sampleRate);
        byte[] generatedSnd = new byte[2 * numSamples];
        for (int i = 0; i < numSamples; ++i) {
            double angle = 2 * Math.PI * i / (sampleRate / freqOfTone);
            generatedSnd[i] = (byte) (Math.sin(angle) * 127);
        }

        audioTrack.play();
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.stop();
        audioTrack.release();
    }
}
