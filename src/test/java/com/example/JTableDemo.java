package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableDemo {


    static String[][] data = {
            {"China", "Beijing", "Chinese"},
            {"America", "Washington", "English"},
            {"Korea", "Seoul", "Korean"},
            {"Japan", "Tokyo", "Japanese"},
            {"France", "Paris", "French"},
            {"England", "London", "English"},
            {"Germany", "Berlin", "German"},
    };
    static String[] titles = {"Country", "Capital", "Language"};

    public static void main(String[] args) {
        DefaultTableModel m = new DefaultTableModel(data, titles);
        JTable t = new JTable(m);
        final TableRowSorter sorter = new TableRowSorter(m);
        t.setRowSorter(sorter); //为JTable设置排序器

        JScrollPane sPane = new JScrollPane();
        sPane.setViewportView(t);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        JLabel l = new JLabel("Criteria:");
        final JTextField tf = new JTextField();
        JButton b = new JButton("Do Filter");
        p.add(l);
        p.add(tf);
        p.add(b);
        b.addActionListener(e -> {
            if (tf.getText().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter(tf.getText()));//为JTable设置基于正则表达式的过滤条件
            }
        });

        JFrame f = new JFrame("JTable Sorting and Filtering");
        f.getContentPane().add(sPane, BorderLayout.CENTER);
        f.getContentPane().add(p, BorderLayout.SOUTH);
        f.setSize(400, 300);
        f.setVisible(true);
    }
}
