package com.example.parts.action.parts;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public enum RemovePane {

    INSTANCE;

    private final JPanel removePane;

    RemovePane() {
        removePane = new JPanel(new MigLayout("inset 20", "[][sizegroup 1]", ""));
        removePane.add(new JLabel("删除字符个数："));
        removePane.add(new JSpinner(), "wrap 15,width 148!");
        removePane.add(new JLabel("开始下标："));
        removePane.add(new JSpinner(), "wrap 15,growx");
        removePane.add(new JLabel("计数方向："));
        JComboBox<String> jComboBox1 = new JComboBox<>();
        jComboBox1.addItem("从前向后");
        jComboBox1.addItem("从后向前");
        removePane.add(jComboBox1, "growx");
    }

    public JPanel getInstance() {
        return removePane;
    }
}
