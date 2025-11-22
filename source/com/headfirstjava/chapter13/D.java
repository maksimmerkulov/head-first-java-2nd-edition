package com.headfirstjava.chapter13;

import javax.swing.*;
import java.awt.*;

public class D {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        JButton button = new JButton("tesuji");
        JButton buttonTwo = new JButton("watari");
        frame.getContentPane().add(BorderLayout.NORTH,panel);
        panel.add(buttonTwo);
        frame.getContentPane().add(BorderLayout.CENTER,button);
        frame.setSize(250,200);
        frame.setVisible(true);
    }
}
