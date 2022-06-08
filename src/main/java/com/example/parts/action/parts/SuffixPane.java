package com.example.parts.action.parts;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public enum SuffixPane {

    INSTANCE;

    private final JPanel suffixPane;

    SuffixPane() {
        suffixPane = new JPanel();
        suffixPane.setLayout(new MigLayout("inset 20", "[][sizegroup 1]"));
        suffixPane.setBackground(new Color(247, 247, 247));
        suffixPane.add(new JLabel("设置后缀："));
        suffixPane.add(new JTextField(25), "wrap 15,width 172!");
        JCheckBox jCheckBox1 = new JCheckBox("保留拓展名");
        jCheckBox1.setSelected(true);
        suffixPane.add(jCheckBox1, "span 2"); //横跨两列
    }

    public JPanel getInstance() {
        return suffixPane;
    }
}
