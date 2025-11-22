package com.headfirstjava.chapter11;

public class Foo {
    public void go() {
        Laundry laundry = new Laundry();
        try {
            laundry.doLaundry();
        } catch(PantsException pex) {
            // recovery code

        } catch(LingerieException lex) {
            // recovery code
        }
    }
}
