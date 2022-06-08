package com.example.parts.bottom;

import com.example.parts.bottom.listeners.FunctionListener;
import com.example.parts.bottom.parts.ComboBoxItem;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public enum BottomPane {

    INSTANCE;

    private final JPanel bottomPane;

    BottomPane() {
        bottomPane = new JPanel();
        bottomPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
                new Color(209,209,209)));
        bottomPane.setLayout(new MigLayout("insets 12",
                "[fill][fill][fill][fill][grow,fill][button,fill][button,fill]",
                ""));
        bottomPane.setPreferredSize(new Dimension(0, 48));
        // 下拉框
        ComboBoxItem[] items = new ComboBoxItem[] {
                new ComboBoxItem("对文件编号", "sequence"),
                new ComboBoxItem("替换关键字", "replace"),
                new ComboBoxItem("更改拓展名", "extension"),
                new ComboBoxItem("插入关键字", "insert"),
                new ComboBoxItem("删除字符", "remove"),
                new ComboBoxItem("添加前缀", "prefix"),
                new ComboBoxItem("添加后缀", "suffix"),
                new ComboBoxItem("转换大小写", "case")
        };
        JComboBox<ComboBoxItem> functions = new JComboBox<>(new DefaultComboBoxModel<>(items));
        functions.addItemListener(new FunctionListener());
        bottomPane.add(functions, "width 120!");

        JCheckBox checkBox1=new JCheckBox("文件", true);
        JCheckBox checkBox2=new JCheckBox("文件夹", false);
        JCheckBox checkBox3=new JCheckBox("子目录", true);
        bottomPane.add(checkBox1);
        bottomPane.add(checkBox2);
        bottomPane.add(checkBox3);

        JLabel tipLabel = new JLabel("请拖动文件至左侧面板");
        tipLabel.setMinimumSize(new Dimension(20, 0));
        tipLabel.setForeground(Color.GRAY);
        bottomPane.add(tipLabel, "align center,growx 0");

        JButton revertBtn = new JButton("撤销");
        revertBtn.setEnabled(false);
        JButton renameBtn = new JButton("重命名");
        bottomPane.add(revertBtn);
        bottomPane.add(renameBtn, "gapleft 6");
    }

    public JPanel getInstance() {
        return bottomPane;
    }
}
