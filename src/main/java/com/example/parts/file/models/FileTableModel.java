package com.example.parts.file.models;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.Arrays;

public class FileTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int rowData, int column) {
        // 禁止编辑，但能选中行
        return false;
    }

    /**
     * 若列表不含该文件返回是
     */
    public boolean notExist(File file) {
        File listedFile;
        for (int i = 0; i < getRowCount(); i++) {
            listedFile = (File) this.getValueAt(i, 0);
            if (file.getAbsolutePath().equals(listedFile.getAbsolutePath())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 移除所有行
     */
    public void removeAllRows() {
        this.setRowCount(0);
    }

    /**
     * 移除多行（假定行号未排序）
     */
    public void removeRows(int[] rows) {
        Arrays.sort(rows);
        for (int i = rows.length - 1; i >= 0; i--) {
            this.removeRow(rows[i]);
        }
    }

    /**
     * 递归添加子目录文件
     */
    public void addFile(File file) {
        if (this.notExist(file)) {
            this.addRow(new Object[]{file, null});
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        addFile(subFile);
                    }
                }
            }
        }
    }

    /**
     * 递归插入子目录文件
     */
    public void insertFile(File file, int row) {
        if (this.notExist(file)) {
            this.insertRow(row, new Object[]{file, null});
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        insertFile(subFile, row + 1);
                    }
                }
            }
        }
    }
}
