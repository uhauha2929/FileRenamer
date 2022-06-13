package com.example.parts.file.models;

import javax.swing.table.DefaultTableModel;
import java.io.File;

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

}
