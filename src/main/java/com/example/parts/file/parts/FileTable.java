package com.example.parts.file.parts;

import com.example.parts.file.FilePane;
import com.example.parts.file.handlers.FileTransferHandler;
import com.example.parts.file.models.FileTableModel;
import com.example.parts.file.models.LeftCell;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public enum FileTable implements ActionListener {

    INSTANCE;

    private final JTable fileTable;

    private final JScrollPane scrollPane;

    private final JPopupMenu rowPopup = new JPopupMenu();

    private final JPopupMenu blankPopup = new JPopupMenu();

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
        cm.getColumn(0).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BorderLayout());
            rowPanel.setBackground(table.getBackground());
            String absPath = (String) value;
            LeftCell cell = tableModel.getLeftMap().get(absPath);
            // 当前文件名列
            Icon systemIcon = FileSystemView.getFileSystemView().getSystemIcon(cell.getFile());
            rowPanel.add(new JLabel(systemIcon), BorderLayout.WEST);
            rowPanel.add(new JLabel(cell.getFile().getName()), BorderLayout.CENTER);

            // 行选中时的背景色
            if (isSelected) {
                rowPanel.setForeground(table.getSelectionForeground());
                rowPanel.setBackground(table.getSelectionBackground());
            }
            return rowPanel;
        });

        scrollPane = new JScrollPane(fileTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        // 设置成TRUE才能拖动
        fileTable.setFillsViewportHeight(true);
        fileTable.setTransferHandler(new FileTransferHandler());

        // 初始化右键菜单
        createJMenuItem(rowPopup, "文件夹中显示", "showInFolder");
        createJMenuItem(rowPopup, "显示文件详情", "showFileInfo");
        rowPopup.addSeparator();
        createJMenuItem(rowPopup, "从列表中移除", "remove");
        createJMenuItem(rowPopup, "清除列表", "removeAll");
        createJMenuItem(blankPopup, "清除列表", "removeAll");

        fileTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //通过点击位置找到点击为表格中的行
                    int focusedRowIndex = fileTable.rowAtPoint(e.getPoint());
                    // 点击空白处
                    if (focusedRowIndex == -1) {
                        blankPopup.show(e.getComponent(), e.getX(), e.getY());
                    } else {
                        //将表格所选项设为当前右键点击的行
                        fileTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                        //弹出菜单
                        rowPopup.show(fileTable, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    private void createJMenuItem(JPopupMenu menu, String text, String command) {
        JMenuItem item = new JMenuItem(text);
        item.setActionCommand(command);
        item.addActionListener(this);
        menu.add(item);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        FileTableModel model = FileTable.INSTANCE.getFileTableModel();
        if ("remove".equals(command)) {
            int row = fileTable.getSelectedRow();
            int tag = JOptionPane.showConfirmDialog(
                    FilePane.INSTANCE.getInstance(), // 居中于父组件
                    "确认移除第" + row +"行记录吗？",
                    "确认",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (tag == JOptionPane.YES_OPTION) {
                model.removeRow(row);
            }
        } else if ("removeAll".equals(command)) {
            int tag = JOptionPane.showConfirmDialog(
                    FilePane.INSTANCE.getInstance(), // 居中于父组件
                    "确认要移除所有行吗?",
                    "确认",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (tag == JOptionPane.YES_OPTION) {
                model.removeAllRows(); // 移除所有行
            }
        } else if ("showInFolder".equals(command)) {
            String absPath = (String) model.getValueAt(fileTable.getSelectedRow(), 0);
            String osName = System.getProperty("os.name");
            try {
                if (osName.startsWith("Mac")) {
                    // 苹果 传入参数必须要是Array，否则无法打开带空格的目录
                    Runtime.getRuntime().exec(new String[] {"open", "-R", absPath});
                } else if (osName.startsWith("Windows")) {
                    // windows
                    Runtime.getRuntime().exec("explorer /select, " +
                            absPath.replaceAll(" ", "\" \""));
                } else {
                    // unix or linux
                    File parentFile = model.getLeftMap().get(absPath).getFile().getParentFile();
                    String parentDir = parentFile == null ?
                        new File(System.getProperty("user.home")).getAbsolutePath(): parentFile.getAbsolutePath();
                    Runtime.getRuntime().exec("nautilus " + parentDir.replaceAll(" ", "\" \""));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(fileTable, "打开文件夹失败！" + ex.getMessage());
            }
        } else if ("showFileInfo".equals(command)) {
            // todo
        }
    }
}
