package com.headfirstjava.chapter10;

public class TestBox {

    Integer i; // defaults to null
    int j;

    public static void main (String[] args) {
        TestBox t = new TestBox();
        // t.go();
    }

    public void go() {
        // Runtime: Unboxing happens here -> NullPointerException because i == null
        j=i;

        // These lines are never executed because the NPE is thrown on the previous line
        System.out.println(j);
        System.out.println(i);
    }
}
