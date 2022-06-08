package com.example.parts.file.listeners;

import com.example.parts.file.parts.FileTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.io.File;
import java.util.List;

/**
 * 左侧面板拖拽文件或文件夹监听
 */
public class DropFileListener implements DropTargetListener {

    private final JPanel filePane;

    public DropFileListener(JPanel filePane) {
        this.filePane = filePane;
    }

    // 拖拽目标进入区域
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        filePane.setBorder(BorderFactory.createDashedBorder(Color.GRAY, 5, 2));
        filePane.setBackground(Color.WHITE);
    }
    // 拖拽目标离开区域
    @Override
    public void dragExit(DropTargetEvent dte) {
        filePane.setBorder(BorderFactory.createEmptyBorder());
        filePane.setBackground(null);
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {

        boolean isAccept = false;

        try {
            /*
             * 1. 文件: 判断拖拽目标是否支持文件列表数据（即拖拽的是否是文件或文件夹, 支持同时拖拽多个）
             */
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                // 接收拖拽目标数据
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                isAccept = true;

                // 以文件集合的形式获取数据
                java.util.List<File> files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                // 把文件路径输出
                if (files.size() > 0) {
                    DefaultTableModel model = FileTable.INSTANCE.getDefaultModel();
                    for (File file : files) {
                        model.addRow(new Object[]{file.getName(), ""});
                        FileTable.INSTANCE.setModel(model);
                    }
                    filePane.setBorder(BorderFactory.createEmptyBorder());
                    filePane.removeAll();
                    filePane.add(FileTable.INSTANCE.getScrollFileTable());
                    filePane.repaint();
                    filePane.revalidate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果此次拖拽的数据是被接受的, 则必须设置拖拽完成（否则可能会看到拖拽目标返回原位置, 造成视觉上以为是不支持拖拽的错误效果）
        if (isAccept) {
            dtde.dropComplete(true);
        }

    }
    // 拖拽目标在区域内移动
    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }
    // 当前drop操作被修改
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

}
