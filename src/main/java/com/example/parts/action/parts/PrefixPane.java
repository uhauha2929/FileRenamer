package com.example.parts.action.parts;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public enum PrefixPane {

    INSTANCE;

    private final JPanel prefixPane;

    PrefixPane() {
        prefixPane = new JPanel();
        prefixPane.setLayout(new MigLayout("inset 20", "[][sizegroup 1]"));
        prefixPane.setBackground(new Color(247, 247, 247));
        prefixPane.add(new JLabel("设置前缀："));
        prefixPane.add(new JTextField(25), "wrap 15,width 172!");
    }

    public JPanel getInstance() {
        return prefixPane;
    }
}
