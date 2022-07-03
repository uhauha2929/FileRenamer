package com.example.parts.file.models;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileTableModel extends DefaultTableModel {

    // 文件夹最大深度
    public static final int MAX_DEPTH = 3;
    // 绝对路径，LeftCell
    private Map<String, LeftCell> leftMap = new HashMap<>();

    public Map<String, LeftCell> getLeftMap() {
        return leftMap;
    }

    public void setLeftMap(Map<String, LeftCell> leftMap) {
        this.leftMap = leftMap;
    }

    @Override
    public boolean isCellEditable(int rowData, int column) {
        // 禁止编辑，但能选中行
        return false;
    }

    /**
     * 若列表不含该文件返回是
     */
    public boolean deleted(String absPath) {
        return !leftMap.containsKey(absPath) || leftMap.get(absPath).getStatus() == 1;
    }

    /**
     * 移除多行（假定行号未排序）
     */
    public void removeRows(int[] rows) {
        if (rows.length >= this.getRowCount()) {
            removeAllRows();
            return;
        }
        Arrays.sort(rows);
        for (int i = rows.length - 1; i >= 0; i--) {
            removeRow(rows[i]);
        }
    }

    /**
     * 移除单行
     */
    @Override
    public void removeRow(int row) {
        // 删除标志位1
        leftMap.get((String)this.getValueAt(row, 0)).setStatus(1);
        super.removeRow(row);
    }

    /**
     * 移除所有行
     */
    public void removeAllRows() {
        leftMap.clear();
        super.setRowCount(0);
    }

    private void addLeftCell(File file, int depth) {
        if (depth >= MAX_DEPTH) {
            return;
        }
        String absPath = file.getAbsolutePath();
        if (this.deleted(absPath)) {
            this.addRow(new Object[]{absPath, null});
            leftMap.put(absPath, new LeftCell(file, Instant.now()));
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        addLeftCell(subFile, depth + 1);
                    }
                }
            }
        }
    }

    /**
     * 递归添加子目录文件
     */
    public void addLeftCell(File file) {
        addLeftCell(file, 1);
    }

    private void insertLeftCell(File file, int start, int row) {
        if (row - start >= MAX_DEPTH) {
            return;
        }
        String absPath = file.getAbsolutePath();
        if (this.deleted(absPath)) {
            this.insertRow(row, new Object[]{absPath, null});
            leftMap.put(absPath, new LeftCell(file, Instant.now()));
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        insertLeftCell(subFile, start, row + 1);
                    }
                }
            }
        }
    }

    /**
     * 递归插入子目录文件
     */
    public void insertLeftCell(File file, int row) {
        insertLeftCell(file, row, row);
    }



}
