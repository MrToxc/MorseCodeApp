package com.example.morsecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//This class is used to translate letters into morse code
public class Translator {
    HashMap<Character, List<MorseCodeSymbols>> hashMap = new HashMap<>();

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

    public ArrayList<MorseCodeSymbols> getArrRdy(String inputMessage) {
        inputMessage = inputMessage.toLowerCase();
        ArrayList<MorseCodeSymbols> arr = new ArrayList<>();
        char[] arrayInput = inputMessage.toCharArray();
        for (int i = 0; i < arrayInput.length; i++) {
            for (MorseCodeSymbols currentSymbol : hashMap.get(arrayInput[i])) {
                arr.add(currentSymbol);
            }
            arr.add(MorseCodeSymbols.SEPARATOR);
        }
        return arr;
    }

}
