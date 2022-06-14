package com.example.parts.file.parts;

import com.example.parts.file.handlers.FileTransferHandler;
import com.example.parts.file.models.FileTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
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
        // 设置表头不能拖动改变顺序
        fileTable.getTableHeader().setReorderingAllowed(false);
        FileTableModel tableModel = new FileTableModel();
        tableModel.setColumnIdentifiers(new String[]{"当前文件名","修改后文件名"});
        fileTable.setModel(tableModel);
        TableColumnModel cm = fileTable.getColumnModel();
        // 单独设置第一列的显示方式
        // 不要将多个列的渲染方式写在一个fileTable.setDefaultRenderer的if,else里，否则列拖动时会有问题
        cm.getColumn(0).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new BorderLayout());
                rowPanel.setBackground(table.getBackground());
                File file = (File) value;
                // 当前文件名列
                Icon systemIcon = FileSystemView.getFileSystemView().getSystemIcon(file);
                rowPanel.add(new JLabel(systemIcon), BorderLayout.WEST);
                rowPanel.add(new JLabel(file.getName()), BorderLayout.CENTER);

                // 行选中时的背景色
                if (isSelected) {
                    rowPanel.setForeground(table.getSelectionForeground());
                    rowPanel.setBackground(table.getSelectionBackground());
                }
                return rowPanel;
            }
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
