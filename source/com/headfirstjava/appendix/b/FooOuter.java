package com.headfirstjava.appendix.b;

public class FooOuter {
    static class BarInner {
        void sayIt() {
            System.out.println("method of a static inner class");
        }
    }
}

class Test {
    public static void main (String[] args) {
        FooOuter.BarInner foo = new FooOuter.BarInner();
        foo.sayIt();
    }
}
