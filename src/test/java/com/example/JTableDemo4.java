package com.example;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;

public class JTableDemo4 {

    private static class DummyTransferHandler
            extends TransferHandler {
        @Override
        protected Transferable createTransferable(JComponent c) {
            if (c instanceof JTable && ((JTable) c).getSelectionModel().isSelectionEmpty())
                return null;

            return new StringSelection("dummy");
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            String message = String.valueOf(support.getDropLocation());
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, message, "Drop", JOptionPane.PLAIN_MESSAGE);
            });
            return false;
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JTable table = new JTable();
        table.setDragEnabled(true);
        table.setDropMode(DropMode.INSERT_ROWS);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setTransferHandler(new DummyTransferHandler());

        JScrollPane scrollPane = new JScrollPane();

        table.setModel(new DefaultTableModel(
                new Object[][]{{"item 1", "item 1b", "January", "July", 123, null},
                        {"item 2", "item 2b", "February", "August", 456, true},
                        {"item 3", null, "March", null, null, null}, {"item 4", null, "April", null, null, null},
                        {"item 5", null, "May", null, null, null}, {"item 6", null, "June", null, null, null},
                        {"item 7", null, "July", null, null, null}, {"item 8", null, "August", null, null, null},
                        {"item 9", null, "September", null, null, null}, {"item 10", null, "October", null, null, null},
                        {"item 11", null, "November", null, null, null},
                        {"item 12", null, "December", null, null, null},},
                new String[]{"Not editable", "Text", "Combo", "Combo Editable", "Integer", "Boolean"}) {
            Class<?>[] columnTypes = new Class<?>[]{Object.class, Object.class, String.class, String.class,
                    Integer.class, Boolean.class};
            boolean[] columnEditable = new boolean[]{false, true, true, true, true, true};

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnEditable[columnIndex];
            }
        });
        {
            TableColumnModel cm = table.getColumnModel();
            cm.getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox(new DefaultComboBoxModel(
                    new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                            "September", "October", "November", "December"}))));
            cm.getColumn(3).setCellEditor(new DefaultCellEditor(new JComboBox(new DefaultComboBoxModel(
                    new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                            "September", "October", "November", "December"}))));

        }
        table.setAutoCreateRowSorter(true);

        scrollPane.setViewportView(table);

        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
