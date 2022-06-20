package com.example.parts.file.handlers;

import com.example.parts.file.models.FileTableModel;
import com.example.parts.file.parts.FileTable;

import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.*;

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
                            model.insertFile(file, dropLocation.getRow());
                        }
                    } else {
                        for (File file : files) {
                            model.addFile(file);
                        }
                    }
                }
                return true;
            } else if (support.isDataFlavorSupported(rowIndexFlavor)) {
                if (support.isDrop()) { // 拖动行而非复制行
                    int[] rows = (int[]) support.getTransferable().getTransferData(rowIndexFlavor);
                    JTable.DropLocation dropLocation = (JTable.DropLocation) support.getDropLocation();
                    Vector vector = model.getDataVector();
                    Deque<Vector<File>> removedRows = new ArrayDeque<>();
                    int dropRow = dropLocation.getRow();
                    // 为了不影响下标，倒序删除
                    for (int i = rows.length - 1; i >= 0; i--) {
                        if (dropRow > rows[i]) {
                            dropRow--;
                        }
                        removedRows.push((Vector<File>) vector.remove(rows[i]));
                    }
                    while (!removedRows.isEmpty()) {
                        model.insertRow(dropRow, removedRows.pop());
                        dropRow++;
                    }
                    JTable table = FileTable.INSTANCE.getInstance();
                    table.clearSelection(); // 取消选中，否则选中的行会超出范围
                    table.repaint();
                    table.revalidate();
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
}
