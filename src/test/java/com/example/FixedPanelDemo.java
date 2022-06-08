package com.example;

import javax.swing.*;
import java.awt.*;

public class FixedPanelDemo {

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setMaximumSize(new Dimension(100, 0));
        panel.setMinimumSize(new Dimension(100, 0));

        panel.setBackground(Color.RED); // for debug only

        Box box = new Box(BoxLayout.Y_AXIS);

        box.add(Box.createVerticalGlue());
        box.add(panel);
        box.add(Box.createVerticalGlue());

        JFrame frame = new JFrame();
        frame.add(box);
        frame.setSize(new Dimension(200, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setMinimumSize(frame.getMinimumSize());   // cannot be resized-

        frame.setVisible(true);

    }
}
