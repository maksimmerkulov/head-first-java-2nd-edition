package com.headfirstjava.chapter12;

class Foo {
    public static void main (String[] args) {
        MyOuter outerObj = new MyOuter();
        MyOuter.MyInner innerObj = outerObj.new MyInner();
    }
}
