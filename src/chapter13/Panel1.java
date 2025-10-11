package chapter13;

import javax.swing.*;
import java.awt.*;

public class Panel1 {

    public static void main (String[] args) {
        Panel1 gui = new Panel1();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);

        JButton button = new JButton("shock me");

        panel.add(button);
        frame.getContentPane().add(BorderLayout.EAST, panel);

        frame.setSize(250,200);
        frame.setVisible(true);
    }
}
