package com.example.morsecode;

import static com.example.morsecode.MorseCodeSymbols.DOT;
import static com.example.morsecode.MorseCodeSymbols.LINE;
import static com.example.morsecode.MorseCodeSymbols.SEPARATOR;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TranslatorTest {
    @Test
    public void translatorTest() {
        Translator translator = new Translator();
        assertEquals(List.of(DOT, LINE, SEPARATOR, DOT, DOT, DOT, DOT, SEPARATOR, LINE, LINE, LINE, SEPARATOR, DOT, LINE, LINE, LINE, SEPARATOR), translator.getArrRdy("Ahoj"));
    }
}