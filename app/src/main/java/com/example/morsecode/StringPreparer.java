package com.example.morsecode;

import java.text.Normalizer;

/**
 * This class is used to prepare Strings to fit required format
 */
public class StringPreparer {


    private Translator translator = new Translator();

    /**
     * This method remooves accents, turns the string into lowercase.
     * @param string
     * @return
     */
    public String getStringRdy(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.toLowerCase();
        String finalString = "";
        char[] charArray = string.toCharArray();
        for (char currentChar : charArray) {
            if (translator.getHashMap().containsKey(currentChar) && currentChar != '$') {
                finalString += currentChar;
            }
        }
        finalString = finalString.trim();
        return finalString;
    }

    /**
     * This method detects when c and h is next to each other and replaces it with $ (because of czech letter ch,
     * which cant be stored in char array).
     * @param string
     * @return
     */
    public String getCzech(String string) {
        string = string.toLowerCase();
        String finalString = "";
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 'c' && charArray[i+1] == 'h') {
                finalString += '$';
                i++;
            } else finalString += charArray[i];
        }
        return finalString;
    }
}
