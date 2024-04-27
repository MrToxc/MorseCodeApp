package com.example.morsecode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

public class StringPreparerTest {
    @Test
    public void GetStringRdyTest() {
        StringPreparer stringPreparer = new StringPreparer();
        assertEquals("hello world", stringPreparer.getStringRdy(" @HellO, WorLD :"));
    }

    @Test
    public void getCzechTest() {
        StringPreparer stringPreparer = new StringPreparer();
        assertEquals("$ata hro$. $leba", stringPreparer.getCzech("chata hroch. chleba"));
    }


}