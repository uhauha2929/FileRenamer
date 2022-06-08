package com.example.parts.file;

import com.example.parts.GlobalToolBar;
import com.example.parts.file.parts.ListPane;

import javax.swing.*;
import java.awt.*;

public enum FilePane {

    INSTANCE;

    private final JPanel filePane;

    FilePane() {
        filePane = new JPanel();
        filePane.setBorder(BorderFactory.createEmptyBorder());
        filePane.setLayout(new BorderLayout());
        filePane.add(ListPane.INSTANCE.getInstance(), BorderLayout.CENTER);
    }

    public JPanel getInstance() {
        return filePane;
    }
}
