package com.example.parts.action.parts;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public enum InsertPane {

    INSTANCE;

    private final JPanel insertPane;

    InsertPane() {
        insertPane = new JPanel(new MigLayout("inset 20", "[][sizegroup 1]", ""));
        insertPane.add(new JLabel("插入关键字："));
        insertPane.add(new JTextField(), "wrap 15,width 160!");
        insertPane.add(new JLabel("开始下标："));
        insertPane.add(new JSpinner(), "wrap 15,growx");
        insertPane.add(new JLabel("计数方向："));
        JComboBox<String> jComboBox1 = new JComboBox<>();
        jComboBox1.addItem("从前向后");
        jComboBox1.addItem("从后向前");
        insertPane.add(jComboBox1, "growx");
    }

    public JPanel getInstance() {
        return insertPane;
    }
}
