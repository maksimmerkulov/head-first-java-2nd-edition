package com.headfirstjava.chapter15;

class ThreadTestDrive {
    public static void main (String[] args) {
        Runnable theJob = new MyRunnable();
        Thread t = new Thread(theJob);
        t.start();
        System.out.println("back in main");
    }
}
