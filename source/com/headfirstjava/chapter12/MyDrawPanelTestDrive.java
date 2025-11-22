package com.headfirstjava.chapter12;

import javax.swing.*;

public class MyDrawPanelTestDrive {
    public static void main (String[] args) {

        JFrame frame = new JFrame();
        MyDrawPanel panel = new MyDrawPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(panel);

        frame.setSize(300,300);

        frame.setVisible(true);
    }
}
