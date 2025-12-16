package com.headfirstjava.appendix.b;

import java.awt.event.*;
import javax.swing.*;
public class TestAnon {
    public static void main (String[] args) {

        JFrame frame = new JFrame();
        JButton button = new JButton("click");
        frame.getContentPane().add(button);
        // button.addActionListener(quitListener);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
    }
}
