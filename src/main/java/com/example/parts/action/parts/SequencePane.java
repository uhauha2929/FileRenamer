package com.example.parts.action.parts;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public enum SequencePane {

    INSTANCE;

    private final JPanel sequencePane;

    SequencePane() {
        sequencePane = new JPanel(new MigLayout("inset 20", "[][sizegroup 1]", ""));
        sequencePane.add(new JLabel("编号格式："));
        JComboBox<String> jComboBox1 = new JComboBox<>();
        jComboBox1.addItem("1,2,3...");
        jComboBox1.addItem("(1),(2),(3)...");
        sequencePane.add(jComboBox1, "wrap 15,width 170!");
        sequencePane.add(new JLabel("开始数字："));
        sequencePane.add(new JSpinner(), "wrap 15,growx");
        sequencePane.add(new JLabel("递增步长："));
        sequencePane.add(new JSpinner(), "wrap 15,growx");
        sequencePane.add(new JLabel("编号长度："));
        sequencePane.add(new JSpinner(), "wrap 15,growx");
        sequencePane.add(new JLabel("前缀："));
        sequencePane.add(new JTextField(), "wrap 15,growx");
        sequencePane.add(new JLabel("后缀："));
        sequencePane.add(new JTextField(), "wrap 15,growx");
        sequencePane.add(new JCheckBox("是否保留拓展名"), "span 2,wrap 15");
    }

    public JPanel getInstance() {
        return sequencePane;
    }
}
