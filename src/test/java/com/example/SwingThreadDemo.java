package com.example;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SwingThreadDemo extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String STR = "Completed : ";
    private JProgressBar progressBar = new JProgressBar();
    private JTextField text = new JTextField(10);
    private JButton start = new JButton("Start");
    private JButton end = new JButton("End");
    private boolean flag = false;
    private int count = 0;
    public SwingThreadDemo() {
        this.setLayout(new FlowLayout());
        add(progressBar);
        text.setEditable(false);
        add(text);
        add(start);
        add(end);
        start.addActionListener(new Start());
        end.addActionListener(new End());
    }

    // 工作线程
    private void go() {
        while (count < 100) {
            try {
                Thread.sleep(50);//这里比作要完成的某个耗时的工作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //更新进度条和输入框
            if (flag) {
                count++;
                // 涉及Swing的操作提交给事件派发线程，从事件派发线程更新组件
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setValue(count);
                        text.setText(STR + String.valueOf(count) + "%");
                    }
                });
            }
        }
    }
    private class Start implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            flag = true;//设置开始更新的标志
            // 另开一个线程工作，否则会阻塞事件派发线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    go();
                }
            }).start();
        }
    }
    private class End implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            flag = false;//停止
        }
    }
    public static void main(String[] args) {
        SwingThreadDemo fg = new SwingThreadDemo();
        fg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fg.setSize(300, 100);
        fg.setVisible(true);
    }
}

