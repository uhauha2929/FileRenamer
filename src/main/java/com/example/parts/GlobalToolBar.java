package com.example.parts;

import com.example.parts.file.FilePane;
import com.example.parts.file.models.FileTableModel;
import com.example.parts.file.parts.FileTable;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public enum GlobalToolBar implements ActionListener {

    INSTANCE;

    private final JToolBar jToolBar;

    GlobalToolBar() {
        jToolBar = new JToolBar();
        jToolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(209,209,209)));
        JButton sortBtn = new JButton();
        sortBtn.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/sort.svg"))));
        sortBtn.setToolTipText("排序方式");
        jToolBar.add(sortBtn);
        jToolBar.addSeparator();
        JButton addBtn = new JButton();
        addBtn.setIcon(UIManager.getIcon("Tree.openIcon"));
        addBtn.setToolTipText("添加文件");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);
        jToolBar.add(addBtn);
        JButton removeBtn = new JButton();
        removeBtn.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/remove.svg"))));
        removeBtn.setToolTipText("移除文件");
        removeBtn.setActionCommand("remove");
        removeBtn.addActionListener(this);
        jToolBar.add(removeBtn);
        JButton clearBtn = new JButton();
        clearBtn.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/clear.svg"))));
        clearBtn.setToolTipText("清除所有");
        clearBtn.setActionCommand("removeAll");
        clearBtn.addActionListener(this);
        jToolBar.add(clearBtn);
    }

    public JToolBar getInstance() {
        return jToolBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable fileTable = FileTable.INSTANCE.getInstance();
        FileTableModel model = FileTable.INSTANCE.getFileTableModel();
        if (e.getActionCommand().equals("removeAll")) {
            int tag = JOptionPane.showConfirmDialog(
                    FilePane.INSTANCE.getInstance(), // 居中于父组件
                    "确认要移除所有行吗?",
                    "确认",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (tag == JOptionPane.YES_OPTION) {
                model.setRowCount(0); // 移除所有行
            }
        } else if (e.getActionCommand().equals("remove")) {
            int[] rows = fileTable.getSelectedRows();
            int tag = JOptionPane.showConfirmDialog(
                    FilePane.INSTANCE.getInstance(), // 居中于父组件
                    "确认移除" + rows.length +"行记录吗？",
                    "确认",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (tag == JOptionPane.YES_OPTION) {
                for (int i = rows.length - 1; i >= 0; i--) {
                    model.removeRow(rows[i]);
                }
            }
        } else if (e.getActionCommand().equals("add")) {
            JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
            // 可以选择文件或文件夹
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            // 支持多选
            fc.setMultiSelectionEnabled(true);
            int val = fc.showOpenDialog(FilePane.INSTANCE.getInstance());
            if (val== JFileChooser.APPROVE_OPTION) {
                //正常选择文件
                File[] files = fc.getSelectedFiles();
                for (File file : files) {
                    model.addFile(file);
                }
            }
        }
    }
}
