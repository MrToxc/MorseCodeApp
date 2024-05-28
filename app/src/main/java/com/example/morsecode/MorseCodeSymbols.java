package com.example.morsecode;

import androidx.annotation.NonNull;

public enum MorseCodeSymbols {
    DOT("."), LINE("-"), SEPARATOR("|"), SPACE("||"), PERIOD("|||");

    private final String stringValue;

    private MorseCodeSymbols(String stringValue) {
        this.stringValue = stringValue;
    }

    @NonNull
    @Override
    public String toString() {
        return stringValue;
    }
}
