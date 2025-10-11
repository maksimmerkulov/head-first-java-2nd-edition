package chapter13;

import javax.swing.*;
import java.awt.*;

public class Button1 {

    public static void main (String[] args) {
        Button1 gui = new Button1();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        JButton button = new JButton("There is no spoon...");
        frame.getContentPane().add(BorderLayout.NORTH, button);
        frame.setSize(200,200);
        frame.setVisible(true);
    }
}
