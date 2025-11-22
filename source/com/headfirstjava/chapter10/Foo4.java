package com.headfirstjava.chapter10;

// This compiles
public class Foo4 {
    static final int x = 12;

    public void go() {
        System.out.println(x); // OK: x is a static constant
    }
}
