package com.example.parts.file.parts;

import com.example.parts.file.listeners.DropFileListener;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.util.Objects;

public enum ListPane {

    INSTANCE;

    private final JPanel listPane;

    ListPane() {
        listPane = new JPanel();
        listPane.setLayout(new BorderLayout());
        listPane.setPreferredSize(new Dimension(600, 450)); // 设置宽度
        FlatSVGIcon flatSVGIcon = new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/drag-drop.svg")));
        JLabel dragLabel = new JLabel(flatSVGIcon);
        listPane.add(dragLabel);
        // 注册拖拽监听器
        new DropTarget(listPane,
                DnDConstants.ACTION_COPY_OR_MOVE,
                new DropFileListener(listPane),
                true);
    }

    public JPanel getInstance() {
        return listPane;
    }
}
