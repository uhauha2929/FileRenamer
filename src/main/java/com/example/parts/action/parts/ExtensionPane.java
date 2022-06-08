package com.example.parts.action.parts;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public enum ExtensionPane {

    INSTANCE;

    private final JPanel extensionPane;

    ExtensionPane () {
        extensionPane = new JPanel();
        extensionPane.setLayout(new MigLayout("inset 20", "[][sizegroup 1]"));
        extensionPane.setBackground(new Color(247, 247, 247));
        extensionPane.add(new JLabel("拓展名："));
        extensionPane.add(new JTextField(), "wrap 15,width 180!");
        extensionPane.add(new JCheckBox("只修改存在的拓展名"), "span 2"); //横跨两列
    }

    public JPanel getInstance() {
        return extensionPane;
    }
}
