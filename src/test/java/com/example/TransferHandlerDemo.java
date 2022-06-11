package com.example;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;

class FileTransferHandler extends TransferHandler {
    @Override
    public boolean importData(JComponent comp, Transferable t) {
        try {
            List<File> list = (List<File>) t
                    .getTransferData(DataFlavor.javaFileListFlavor);
            for (File f : list) {
                System.out.println(f.getAbsolutePath());
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

}


public class TransferHandlerDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLocation(300, 400);
        frame.setSize(300, 200);
        JTextArea area = new JTextArea("拖动文件到此打印路径");
        area.setTransferHandler(new FileTransferHandler());
        frame.add(area);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
