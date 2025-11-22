package com.headfirstjava.chapter04;

// Won’t compile.
class Foo {
    public void go() {
        int x = 0; // added initialization after verified that it doesn't compile without it
        int z = x + 3;
    }
}
