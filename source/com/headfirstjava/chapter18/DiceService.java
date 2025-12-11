package com.headfirstjava.chapter18;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DiceService implements Service {

    JComboBox numOfDice;
    DicePanel dicePanel;

    public JPanel getGuiPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Roll 'em!");
        String[] choices = {"1", "2", "3", "4", "5"};
        numOfDice = new JComboBox(choices);
        dicePanel = new DicePanel();
        button.addActionListener(new RollEmListener());
        panel.add(numOfDice);
        panel.add(button);
        panel.add(dicePanel);
        return panel;
    }

    class RollEmListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            // roll the dice
            String selection = (String) numOfDice.getSelectedItem();
            int numOfDiceToRoll = Integer.parseInt(selection);
            int[] rolls = new int[numOfDiceToRoll];
            for (int i = 0; i < numOfDiceToRoll; i++) {
                rolls[i] = (int) (Math.random() * 6) + 1;
            }
            dicePanel.setValues(rolls);
        }
    }

    class DicePanel extends JPanel {
        int[] values = new int[0];

        public DicePanel() {
            setPreferredSize(new Dimension(250, 120));
        }

        public void setValues(int[] values) {
            this.values = values;
            repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (values == null || values.length == 0) return;

            int x = 10;
            for (int i = 0; i < values.length; i++) {
                drawDie(g, x, 20, 40, values[i]);
                x += 45;
            }
        }

        private void drawDie(Graphics g, int x, int y, int size, int value) {
            g.setColor(new Color(230, 230, 230));
            g.fillRect(x, y, size, size);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, size, size);

            int dotSize = 10;
            int cx = x + size / 2;
            int cy = y + size / 2;

            if (value == 1) {
                drawDot(g, cx, cy, dotSize);
            } else if (value == 2) {
                drawDot(g, x + size/4, y + size/4, dotSize);
                drawDot(g, x + 3*size/4, y + 3*size/4, dotSize);
            } else if (value == 3) {
                drawDot(g, cx, cy, dotSize);
                drawDot(g, x + size/4, y + size/4, dotSize);
                drawDot(g, x + 3*size/4, y + 3*size/4, dotSize);
            } else if (value == 4) {
                drawDot(g, x + size/4, y + size/4, dotSize);
                drawDot(g, x + 3*size/4, y + size/4, dotSize);
                drawDot(g, x + size/4, y + 3*size/4, dotSize);
                drawDot(g, x + 3*size/4, y + 3*size/4, dotSize);
            } else if (value == 5) {
                drawDot(g, cx, cy, dotSize);
                drawDot(g, x + size/4, y + size/4, dotSize);
                drawDot(g, x + 3*size/4, y + size/4, dotSize);
                drawDot(g, x + size/4, y + 3*size/4, dotSize);
                drawDot(g, x + 3*size/4, y + 3*size/4, dotSize);
            } else if (value == 6) {
                drawDot(g, x + size/4, y + size/4, dotSize);
                drawDot(g, x + 3*size/4, y + size/4, dotSize);
                drawDot(g, x + size/4, cy, dotSize);
                drawDot(g, x + 3*size/4, cy, dotSize);
                drawDot(g, x + size/4, y + 3*size/4, dotSize);
                drawDot(g, x + 3*size/4, y + 3*size/4, dotSize);
            }
        }

        private void drawDot(Graphics g, int x, int y, int size) {
            g.fillOval(x - size/2, y - size/2, size, size);
        }
    }
}
