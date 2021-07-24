package com.example.filipino_slang;

import java.util.ArrayList;
import java.util.Random;

public class Words {

    private String[] word, desription;
    private char[] letters;
    private Random rand = new Random();

    public Words(String[] word, String[] description){
        this.word = word;
        this.desription = description;
    }

    public String[] getWord() { return this.word; }

    public String[] getDesription() {
        return this.desription;
    }


}
