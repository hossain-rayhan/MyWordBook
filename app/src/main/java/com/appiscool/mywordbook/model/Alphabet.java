package com.appiscool.mywordbook.model;

/**
 * Created by Rayhan on 6/13/2015.
 */
public class Alphabet {
    private String alphabet;
    private String count;

    public Alphabet(String alphabet, String count) {
        this.alphabet = alphabet;
        this.count = count;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
