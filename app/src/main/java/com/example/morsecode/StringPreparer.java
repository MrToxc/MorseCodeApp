package com.example.morsecode;

public class StringPreparer {


    private Translator translator = new Translator();

    public String getStringRdy(String string) {
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

    public String getCzech(String string) {
        string = string.trim();
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
