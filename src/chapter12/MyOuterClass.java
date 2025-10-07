package chapter12;

class MyOuterClass {

    private int x;

    class MyInnerClass {
        void go() {
            x = 42;
        }
    } // close inner class

} // close outer class
