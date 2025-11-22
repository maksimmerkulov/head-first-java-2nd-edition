package com.headfirstjava.chapter12;

class MyOuter {

    private int x;

    MyInner inner = new MyInner();

    public void doStuff() {
        inner.go();
    }

    class MyInner {
        void go() {
            x = 42;
        }
    } // close inner class

} // close outer class
