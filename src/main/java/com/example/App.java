package com.example;

import com.example.parts.bottom.BottomPane;
import com.example.parts.action.ActionPane;
import com.example.parts.file.FilePane;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame jf = new JFrame("文件批量重命名工具");    //创建一个JFrame对象
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(500, 100, 0, 0);    //设置窗口大小和位置
        JPanel contentPane = new JPanel();    //创建一个主面板
        jf.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        JPanel filePane = FilePane.INSTANCE.getInstance(); // 左边的文件信息面板
        JPanel bottomPane = BottomPane.INSTANCE.getInstance(); // 底部面板
        JScrollPane actionScrollPane = ActionPane.INSTANCE.getScrollPane(); // 右边的带滚动条的功能面板
        contentPane.add(filePane, BorderLayout.CENTER); // 文件区域定为中心，缩放时也跟着缩放
        contentPane.add(actionScrollPane, BorderLayout.EAST);
        contentPane.add(bottomPane, BorderLayout.SOUTH);
        jf.setMinimumSize(new Dimension(530, 250));
        jf.pack();  // 自适应组件的最优大小
        jf.setVisible(true);    //设置窗口可见，放在最后
    }
}
