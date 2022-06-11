package com.example.parts.file.parts;

import com.example.parts.file.models.FileTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;

public enum FileTable {

    INSTANCE;

    private final JTable fileTable;

    private final JScrollPane scrollFileTable;

    FileTable() {
        fileTable = new JTable();
        scrollFileTable = new JScrollPane(fileTable);
        scrollFileTable.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0))); // 无边框
        FileTableModel tableModel = new FileTableModel();
        tableModel.setColumnNames(new String[]{"当前文件名","变更后文件名"});
        fileTable.setModel(tableModel);
        fileTable.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BorderLayout());
            rowPanel.setBackground(table.getBackground());
            if (column == 0) {
                File file = (File) value;
                Icon systemIcon = FileSystemView.getFileSystemView().getSystemIcon(file);
                rowPanel.add(new JLabel(systemIcon), BorderLayout.WEST);
                rowPanel.add(new JLabel(file.getName()), BorderLayout.CENTER);
                rowPanel.setBackground(table.getBackground());
            }

            // 行选中时的背景色
            if (isSelected) {
                rowPanel.setForeground(table.getSelectionForeground());
                rowPanel.setBackground(table.getSelectionBackground());
            }
            return rowPanel;
        });
    }

    public DefaultTableModel getDefaultModel() {
        return (DefaultTableModel) fileTable.getModel();
    }

    public FileTableModel getFileTableModel() {
        return (FileTableModel) fileTable.getModel();
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
