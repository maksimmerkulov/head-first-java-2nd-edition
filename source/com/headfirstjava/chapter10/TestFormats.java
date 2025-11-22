package com.headfirstjava.chapter10;

public class TestFormats {

    public static void main (String[] args) {

        String s = String.format("%, d", 1000000000);
        System.out.println(s);
    }
}
