package com.example.parts.file.models;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTableModel extends AbstractTableModel {

    private ArrayList<ArrayList<Object>> fileMatrix;
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
        Object item = this.fileMatrix.get(rowIndex).get(columnIndex);
        if (item == null) {
            return null;
        }
        if (columnIndex != 0) {
            File file = (File) item;
            return file.getName();
        }
        return item;
    }

    public ArrayList<ArrayList<Object>> getFileMatrix() {
        return fileMatrix;
    }

    public void setFileMatrix(ArrayList<ArrayList<Object>> fileMatrix) {
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

    public void addRow(Object[] items) {
        ArrayList<Object> row = new ArrayList<>(Arrays.asList(items));
        this.fileMatrix.add(row);
    }

    // JTable显示的列名
    public String getColumnName(int column) {
        Object id = null;
        if (column < columnNames.size() && (column >= 0)) {
            id = columnNames.get(column);
        }
        return (id == null) ? super.getColumnName(column)
                : id.toString();
    }

    public boolean contains(File file) {
        File listedFile;
        for (int i = 0; i < getRowCount(); i++ ) {
            listedFile = (File) this.fileMatrix.get(i).get(0);
            if (file.getAbsolutePath().equals(listedFile.getAbsolutePath())) {
                return true;
            }
        }
        return false;
    }

}
