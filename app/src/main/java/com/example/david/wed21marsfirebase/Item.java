package com.example.david.wed21marsfirebase;

/**
 * Created by david on 2018-03-21.
 */

public class Item {
    String name;
    boolean done;

    public Item() {}

    public Item(String name, boolean done ) {
        this.name = name;
        this.done = done;
    }

    public void switchDone() {
        done =! done;
    }
}
