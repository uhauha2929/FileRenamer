package com.example.parts.action.parts;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public enum CasePane {

    INSTANCE;

    private final JPanel casePane;

    CasePane() {
        casePane = new JPanel(new MigLayout("inset 20", "[][sizegroup 1]", ""));
        casePane.add(new JLabel("文件名："));
        JComboBox<String> jComboBox1 = new JComboBox<>();
        jComboBox1.addItem("全部转大写");
        jComboBox1.addItem("全部转小写");
        jComboBox1.addItem("转为标题样式");
        jComboBox1.addItem("转为句子样式");
        casePane.add(jComboBox1, "wrap 15,width 180!");
        casePane.add(new JLabel("拓展名："));
        JComboBox<String> jComboBox2 = new JComboBox<>();
        jComboBox2.addItem("保持不变");
        jComboBox2.addItem("转大写");
        jComboBox2.addItem("转小写");
        casePane.add(jComboBox2, "wrap 15,growx");
    }

    public JPanel getInstance() {
        return casePane;
    }
}
