package com.example.parts.file.parts;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public enum FileToolBar {

    INSTANCE;

    private final JToolBar jToolBar;

    FileToolBar() {
        jToolBar = new JToolBar();
        jToolBar.setOrientation(JToolBar.VERTICAL);
        jToolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                new Color(209,209,209)));
        JButton addBtn = new JButton();
//        addBtn.setActionCommand("add");
        addBtn.setIcon(UIManager.getIcon("Tree.closedIcon"));
        addBtn.setToolTipText("添加一个文件");
        jToolBar.add(addBtn);
    }

    public JToolBar getInstance() {
        return jToolBar;
    }
}
