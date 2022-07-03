package com.example.parts.file;

import com.example.parts.file.parts.FileTable;

import javax.swing.*;
import java.awt.*;

public enum FilePane {

    INSTANCE;

    private final JPanel filePane;

    FilePane() {
        filePane = new JPanel(new BorderLayout());
        filePane.setPreferredSize(new Dimension(600, 400));
        filePane.add(FileTable.INSTANCE.getScrollPane(), BorderLayout.CENTER);
        filePane.setBorder(BorderFactory.createEmptyBorder());
    }

    public JPanel getInstance() {
        return filePane;
    }

}
