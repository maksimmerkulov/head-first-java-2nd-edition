package com.headfirstjava.chapter15;

public class MyRunnable implements Runnable {

    public void run() {
        go();
    }

    public void go() {

        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }

        doMore();
    }

    public void doMore() {
        System.out.println("top o' the stack");
    }
}
