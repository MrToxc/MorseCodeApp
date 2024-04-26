package com.example.morsecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//This class is used to translate letters into morse code
public class Translator {
    HashMap<Character, List<MorseCodeSymbols>> hashMap = new HashMap<>();

    public Translator() {
        hashMap.put('a', List.of(MorseCodeSymbols.DOT, MorseCodeSymbols.LINE));
        hashMap.put('b', List.of());
        hashMap.put('c', List.of());
        hashMap.put('d', List.of());
        hashMap.put('e', List.of());
        hashMap.put('f', List.of());
        hashMap.put('g', List.of());
        hashMap.put('h', List.of());
        hashMap.put('i', List.of());
        hashMap.put('j', List.of());
        hashMap.put('k', List.of());
        hashMap.put('l', List.of());
        hashMap.put('m', List.of());
        hashMap.put('n', List.of());
        hashMap.put('o', List.of());
        hashMap.put('p', List.of());
        hashMap.put('q', List.of());
        hashMap.put('r', List.of());
        hashMap.put('s', List.of());
        hashMap.put('t', List.of());
        hashMap.put('u', List.of());
        hashMap.put('v', List.of());
        hashMap.put('w', List.of());
        hashMap.put('x', List.of());
        hashMap.put('y', List.of());
        hashMap.put('z', List.of());
        hashMap.put(' ', List.of());
        hashMap.put('.', List.of());
        hashMap.put('0', List.of());
        hashMap.put('1', List.of());
        hashMap.put('2', List.of());
        hashMap.put('3', List.of());
        hashMap.put('4', List.of());
        hashMap.put('5', List.of());
        hashMap.put('6', List.of());
        hashMap.put('7', List.of());
        hashMap.put('8', List.of());
        hashMap.put('9', List.of());
    }

    public ArrayList<MorseCodeSymbols> translate(String input) {
        ArrayList<MorseCodeSymbols> result = new ArrayList<>();
        char[] arrayInput = input.toCharArray();
        //TBC

        return result;
    }
}
