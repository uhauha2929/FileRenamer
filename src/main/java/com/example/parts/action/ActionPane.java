package com.example.parts.action;

import com.example.parts.action.parts.SequencePane;

import javax.swing.*;
import java.awt.*;

public enum ActionPane {

    INSTANCE;

    private final JPanel actionPane;

    private final JScrollPane scrollPane;

    ActionPane() {
        actionPane = new JPanel(new BorderLayout());
        actionPane.add(SequencePane.INSTANCE.getInstance(), BorderLayout.CENTER);
        scrollPane = new JScrollPane(actionPane);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0,
                new Color(209,209,209)));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // 隐藏水平滚动条
    }

    public JPanel getInstance() {
        return actionPane;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

}
