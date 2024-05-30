package com.example.morsecode;

import androidx.annotation.NonNull;

/**
 * ENUM with all morse code symbols needed.
 * Also includes their toString();
 */

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
