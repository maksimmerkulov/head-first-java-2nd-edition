package com.headfirstjava.chapter10;

public class Duck {

    private int size;
    private static int duckCount = 0;

    public Duck() {
        duckCount++;
    }

    public void setSize(int s) {
        size = s;
    }
    public int getSize() {
        return size;
    }
}
