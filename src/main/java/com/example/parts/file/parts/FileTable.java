package com.example.parts.file.parts;

import com.example.parts.file.handlers.FileTransferHandler;
import com.example.parts.file.models.FileTableModel;
import javafx.scene.control.SelectionMode;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;

public enum FileTable {

    INSTANCE;

    private final JTable fileTable;

    private final JScrollPane scrollPane;

    FileTable() {
        fileTable = new JTable();
        fileTable.setDragEnabled(true);
        fileTable.setDropMode(DropMode.INSERT_ROWS);
        fileTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // 设置表头不能拖动改变顺序（否则拖动有问题）
        fileTable.getTableHeader().setReorderingAllowed(false);
        FileTableModel tableModel = new FileTableModel();
        tableModel.setColumnIdentifiers(new String[]{"当前文件名","修改后文件名"});
        fileTable.setModel(tableModel);
        // 设置单元格的显示方式
        fileTable.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BorderLayout());
            rowPanel.setBackground(table.getBackground());
            File file = (File) value;
            // 当前文件名列
            if (column == 0) {
                Icon systemIcon = FileSystemView.getFileSystemView().getSystemIcon(file);
                rowPanel.add(new JLabel(systemIcon), BorderLayout.WEST);
                rowPanel.add(new JLabel(file.getName()), BorderLayout.CENTER);
                rowPanel.setBackground(table.getBackground());
            }
            // 修改后的文件名列
            if (column == 1) {
                if (file != null) {
                    rowPanel.add(new JLabel(file.getName()), BorderLayout.CENTER);
                }
                rowPanel.setBackground(table.getBackground());
            }
            // 行选中时的背景色
            if (isSelected) {
                rowPanel.setForeground(table.getSelectionForeground());
                rowPanel.setBackground(table.getSelectionBackground());
            }
            return rowPanel;
        });

        scrollPane = new JScrollPane(fileTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(600, 400));
        fileTable.setFillsViewportHeight(true);
        fileTable.setTransferHandler(new FileTransferHandler());
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

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
