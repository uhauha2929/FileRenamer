package com.example.parts.file.handlers;

import com.example.parts.file.models.FileTableModel;
import com.example.parts.file.parts.FileTable;

import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("all")
public class FileTransferHandler extends TransferHandler {

    private final DataFlavor rowIndexFlavor = new DataFlavor(int[].class, "Integer Row Index");

    /**
     * 将要导出的数据绑定到Transferable对象中，为传输做准备
     */
    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof JTable) {
            int[] selectedRows = ((JTable) c).getSelectedRows();
            return new DataHandler(selectedRows, rowIndexFlavor.getMimeType());
        }
        return new StringSelection("dummy");
    }

    /**
     * 查询原组件支持哪些动作，如COPY，MOVE或LINK的任意组合
     */
    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    /**
     * 在成功拖放(或粘贴)时调用此方法，并启动将数据传输到目标组件的操作
     */
    @Override
    public boolean importData(TransferSupport support) {
        FileTableModel model = FileTable.INSTANCE.getFileTableModel();
        try {
            if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List<File> files = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                if (files.size() > 0) {
                    if (support.isDrop()) {
                        JTable.DropLocation dropLocation = (JTable.DropLocation) support.getDropLocation();
                        for (File file : files) {
                            insertSubDir(model, file, dropLocation.getRow());
                        }
                    } else {
                        for (File file : files) {
                            addSubDir(model, file);
                        }
                    }
                }
                return true;
            } else if (support.isDataFlavorSupported(rowIndexFlavor)) {
                if (support.isDrop()) { // 拖动行而非复制行
                    int[] rows = (int[]) support.getTransferable().getTransferData(rowIndexFlavor);
                    JTable.DropLocation dropLocation = (JTable.DropLocation) support.getDropLocation();
                    Vector vector = model.getDataVector();
                    List<Vector<File>> removedRows = new ArrayList<>();
                    int dropRow = dropLocation.getRow();
                    // todo
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return true;
    }

    /**
     * 当操作为MOVE时，传输完成后需要从源中删除数据-此方法是进行任何必要的清理的地方
     */
    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
    }

    /**
     * 递归添加子目录文件
     */
    private void addSubDir(FileTableModel model, File file) {
        if (model.notExist(file)) {
            model.addRow(new Object[]{file, null});
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        addSubDir(model, subFile);
                    }
                }
            }
        }
    }

    /**
     * 递归插入子目录文件
     */
    private void insertSubDir(FileTableModel model, File file, int row) {
        if (model.notExist(file)) {
            model.insertRow(row, new Object[]{file, null});
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        insertSubDir(model, subFile, row + 1);
                    }
                }
            }
        }
    }
}
