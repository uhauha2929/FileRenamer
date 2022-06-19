package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

class MyFrame extends JFrame {

    //弹出式菜单
    JPopupMenu popup = new JPopupMenu();

    public MyFrame(String title) {
        super(title);

        //内容面板
        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new BorderLayout());

        //右键菜单
        popup.add(createMenuItem("ic_open.png", "fileOpen", "打开"));
        popup.add(createMenuItem("ic_save.png", "fileSave", "保存"));
        popup.add(createMenuItem("ic_saveas.png", "fileSaveAs", "另存为"));
        popup.addSeparator();
        popup.add(createMenuItem("ic_help.png", "fileHelp", "帮助"));

        //添加鼠标响应事件，当点击右键时，弹出菜单
        root.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

        });
    }

    protected JMenuItem createMenuItem(String iceName, String action, String text) {
        JMenuItem item = new JMenuItem(text);
        item.setActionCommand(action);
        item.addActionListener(actionListener);
//        if (iceName != null) {
//            String imagePath = "/images/" + iceName;
//            URL imageURL = getClass().getResource(imagePath);
//            item.setIcon(new ImageIcon(imageURL));
//        }
        return item;
    }

    //创建一个监听器
    //注意：actionListener是类的属性
    private ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            System.out.println("执行命令：" + action);

            //当是打开命令时，再弹出一个窗口
            if (action.equals("fileOpen")) {
                JOptionPane.showMessageDialog(MyFrame.this, action);
            }

        }

    };

}

public class JPopupMenuDemo {

    private static void createGUI()
    {
        // JFrame指一个窗口，构造方法的参数为窗口标题
        JFrame frame = new MyFrame("Swing Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口的其他参数，如窗口大小
        frame.setSize(400, 300);

        // 显示窗口
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                createGUI();
            }
        });

    }
}
