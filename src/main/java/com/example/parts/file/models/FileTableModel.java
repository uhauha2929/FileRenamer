package com.example.parts.file.models;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class FileTableModel extends AbstractTableModel {

    private ArrayList<ArrayList<File>> fileMatrix;
    private List<String> columnNames;

    public FileTableModel() {
        fileMatrix = new ArrayList<>();
        columnNames = new ArrayList<>();
    }

    public FileTableModel(String[] columnNames) {
        this(Arrays.asList(columnNames));
    }

    public FileTableModel(List<String> columnNames) {
        fileMatrix = new ArrayList<>();
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return this.fileMatrix.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.size();
    }

    // JTable内显示的文件名
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        File file = this.fileMatrix.get(rowIndex).get(columnIndex);
        return file == null ? "" : file.getName();
    }

    public ArrayList<ArrayList<File>> getFileMatrix() {
        return fileMatrix;
    }

    public void setFileMatrix(ArrayList<ArrayList<File>> fileMatrix) {
        this.fileMatrix = fileMatrix;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = Arrays.asList(columnNames);
    }

    public void addRow(File[] files) {
        ArrayList<File> files1 = new ArrayList<>(Arrays.asList(files));
        this.fileMatrix.add(files1);
    }

    // JTable显示的列名
    public String getColumnName(int column) {
        Object id = null;
        // This test is to cover the case when
        // getColumnCount has been subclassed by mistake ...
        if (column < columnNames.size() && (column >= 0)) {
            id = columnNames.get(column);
        }
        return (id == null) ? super.getColumnName(column)
                : id.toString();
    }

    public boolean contains(File file) {
        for (int i = 0; i < getRowCount(); i++ ) {
            if (file.getAbsolutePath().equals(this.fileMatrix.get(i).get(0).getAbsolutePath())) {
                return true;
            }
        }
        return false;
    }

}
