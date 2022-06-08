package com.example.parts;

import javax.swing.*;
import java.awt.*;

public enum GlobalToolBar {

    INSTANCE;

    private final JToolBar jToolBar;

    GlobalToolBar() {
        jToolBar = new JToolBar();
        jToolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(209,209,209)));
        JButton closeBtn = new JButton();
        closeBtn.setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
        jToolBar.add(closeBtn);
        JButton minBtn = new JButton();
        minBtn.setIcon(UIManager.getIcon("InternalFrame.minimizeIcon"));
        jToolBar.add(minBtn);
        JButton maxBtn = new JButton();
        maxBtn.setIcon(UIManager.getIcon("InternalFrame.maximizeIcon"));
        jToolBar.add(maxBtn);
        jToolBar.addSeparator();
        JButton addBtn = new JButton();
        addBtn.setIcon(UIManager.getIcon("Tree.openIcon"));
        addBtn.setToolTipText("添加一个文件");
        jToolBar.add(addBtn);

    }

    public JToolBar getInstance() {
        return jToolBar;
    }
}
