package com.teamnet.examples;

/**
 * Created by Cosmin.Adamut on 7/10/2017.
 */
public class MyUnit {
    public String concatenate(String one, String two) {
        if (one == null || two == null)
            return null;
        return one + two;
    }
    public boolean getBoolean(){
        return new java.util.Random().nextBoolean();
    }

}
