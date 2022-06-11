package com.example.parts.file.handlers;

import com.example.parts.file.models.FileTableModel;
import com.example.parts.file.parts.FileTable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;

public class FileTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {
//        if (c instanceof JTable && ((JTable) c).getSelectionModel().isSelectionEmpty()) return null;
        return new StringSelection("dummy");
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    public boolean importData(TransferSupport support) {
        try {
            if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List<File> files = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                if (files.size() > 0) {
                    FileTableModel model = FileTable.INSTANCE.getFileTableModel();
                    for (File file : files) {
                        addSubDir(model, file);
                    }
                }
            } else if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String message = String.valueOf(support.getDropLocation());
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, message, "Drop", JOptionPane.PLAIN_MESSAGE);
                });
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return true;
    }

    /**
     * 递归添加子目录文件
     */
    private void addSubDir(FileTableModel model, File file) {
        if (!model.contains(file)) {
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
}
