package com.example.morsecode;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * This class is used to translate letters into morse code
 */
public class Translator {
    HashMap<Character, List<MorseCodeSymbols>> hashMap = new HashMap<>();
    private TextView morseCodeView;

    public void setMorseCodeView(TextView morseCodeView) {
        this.morseCodeView = morseCodeView;
    }

    /**
     * This constructor loads the translator with letters on one side and morse code symbols on the other
     */
    public Translator() {
        hashMap.put('a', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('b', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('c', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT));
        hashMap.put('d', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('e', List.of(MorseCodeSymbols.DOT));
        hashMap.put('f', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT));
        hashMap.put('g', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT));
        hashMap.put('h', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        // dollar sign is in place for czech letter CH
        hashMap.put('$', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('i', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('j', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('k', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('l', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('m', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('n', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT));
        hashMap.put('o', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('p', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT));
        hashMap.put('q', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('r', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT));
        hashMap.put('s', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('t', List.of(MorseCodeSymbols.LINE));
        hashMap.put('u', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('v', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('w', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('x', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('y', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('z', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put(' ', List.of(MorseCodeSymbols.SPACE));
        hashMap.put('.', List.of(MorseCodeSymbols.PERIOD));
        hashMap.put('0', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('1', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('2', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('3', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE));
        hashMap.put('4', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('5', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('6', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('7', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('8', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT, MorseCodeSymbols.DOT));
        hashMap.put('9', List.of(MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.LINE, MorseCodeSymbols.DOT));
    }

    public HashMap<Character, List<MorseCodeSymbols>> getHashMap() {
        return hashMap;
    }

    /**
     * This method takes String as an input and turns it into Arraylist with Morse code symbols
     * @param inputMessage
     * @return
     */
    public ArrayList<MorseCodeSymbols> stringToMorseCode(final String inputMessage) {
        ArrayList<MorseCodeSymbols> morseCode = new ArrayList<>();
        char[] arrayInput = inputMessage.toLowerCase().toCharArray();
        char[] finalArray = new char[100];
        if (arrayInput.length > 100) {
            for (int i = 0; i < 100; i++) {
            finalArray[i] = arrayInput[i];
            }
        } else finalArray = arrayInput;
        for (int i = 0; i < finalArray.length; i++) {
            morseCode.addAll(Objects.requireNonNull(hashMap.get(finalArray[i])));

            if (i == 0 && i < finalArray.length-1) {
                if (finalArray[i] != ' ' && finalArray[i] != '.' && finalArray[i + 1] != ' ' && finalArray[i + 1] != '.') {
                    morseCode.add(MorseCodeSymbols.SEPARATOR);
                }
            } else if (i > 0 && i < finalArray.length-1) {
                if (finalArray[i] != ' ' && finalArray[i] != '.' && finalArray[i + 1] != ' ' && finalArray[i + 1] != '.' ) {
                    morseCode.add(MorseCodeSymbols.SEPARATOR);
                }
            } else if (i > 0 && i > finalArray.length-1) {
                if (finalArray[i] != ' ' && finalArray[i] != '.' && finalArray[i - 1] != ' ' && finalArray[i - 1] != '.') {
                    morseCode.add(MorseCodeSymbols.SEPARATOR);
                }
            } else if (i > 0 && (finalArray[i-1] == '.' || finalArray[i-1] == ' ')) {
                    morseCode.add(MorseCodeSymbols.SEPARATOR);
            }


        }
        if (morseCode.size() > 0) {
            if (morseCode.get(morseCode.size() - 1) == MorseCodeSymbols.SEPARATOR) {
                morseCode.remove(morseCode.size() - 1);
            }
        }
        String morseCodeViewString = "";
        for (MorseCodeSymbols symbol : morseCode) {
            morseCodeViewString += symbol.toString();
        }
        morseCodeView.setText(morseCodeViewString);
        return morseCode;
    }

}
