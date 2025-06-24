package com.headfirstjava.chapter04;

public class XCopy {
    int go(int arg) {
        arg = arg * 2;
        return arg;
    }

    public static void main(String[] args) {
        int orig = 42;
        XCopy x = new XCopy();
        int y = x.go(orig);
        System.out.println(orig + " " + y);
    }
}