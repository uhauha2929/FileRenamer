package com.example.parts.file;

import com.example.parts.file.listeners.DropFileListener;
import com.example.parts.file.parts.FileTable;
import com.example.parts.file.parts.FileToolBar;
import com.example.parts.file.parts.ListPane;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.util.Objects;

public enum FilePane {

    INSTANCE;

    private final JPanel filePane;

    FilePane() {
        filePane = new JPanel();
        filePane.setLayout(new BorderLayout());
        filePane.add(ListPane.INSTANCE.getInstance(), BorderLayout.CENTER);
        filePane.add(FileToolBar.INSTANCE.getInstance(), BorderLayout.WEST);
    }

    public JPanel getInstance() {
        return filePane;
    }
}
