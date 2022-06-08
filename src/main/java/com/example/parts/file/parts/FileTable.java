package com.example.parts.file.parts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public enum FileTable {

    INSTANCE;

    private final JTable fileTable;

    private final JScrollPane scrollFileTable;

    FileTable() {
        fileTable = new JTable();
        scrollFileTable = new JScrollPane(fileTable);
        scrollFileTable.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0))); // 无边框
        DefaultTableModel tableModel = (DefaultTableModel) fileTable.getModel();
        tableModel.setColumnIdentifiers(new Object[]{"当前文件名","变更后文件名"});
        fileTable.setModel(tableModel);
    }

    public DefaultTableModel getDefaultModel() {
        return (DefaultTableModel) fileTable.getModel();
    }

    public void setModel(TableModel model) {
        fileTable.setModel(model);
    }

    public JTable getInstance() {
        return fileTable;
    }

    public JScrollPane getScrollFileTable() {
        return scrollFileTable;
    }
}
